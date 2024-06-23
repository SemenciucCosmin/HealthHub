package com.example.healthhub.presentation.authentication.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.data.authentication.repository.AuthenticationRepository
import com.example.healthhub.data.preferences.repository.PreferencesRepository
import com.example.healthhub.network.resource.Status
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    var uiState by mutableStateOf(AuthenticationUiState())
        private set

    fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(
                email = email,
                password = password,
                isLoading = true,
                isError = false
            )

            val resource = authenticationRepository.login(email, password)
            when (val loginStatus = resource.payload) {
                LoginStatus.EmailError -> register()

                LoginStatus.PasswordError -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isPasswordIncorrectError = true
                    )
                }

                is LoginStatus.Success -> {
                    uiState = uiState.copy(
                        authenticationStep = AuthenticationUiState.Step.AUTHENTICATION_COMPLETED,
                        isLoading = false
                    )

                    preferencesRepository.saveUserInformation(
                        id = loginStatus.userId,
                        email = uiState.email,
                        password = uiState.password
                    )
                }

                else -> uiState = uiState.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    fun uploadImage(idImageUri: Uri) {
        viewModelScope.launch {
            uiState = uiState.copy(
                idImageUri = idImageUri,
                isLoading = true,
                isError = false
            )

            val resource = authenticationRepository.uploadID(uiState.email, idImageUri)
            resource.payload?.let { idValidation ->
                if (idValidation.updated && idValidation.integrity) {
                    uiState = uiState.copy(
                        authenticationStep = AuthenticationUiState.Step.AUTHENTICATION_COMPLETED,
                        isLoading = false
                    )
                }
            } ?: run {
                uiState = if (resource.status == Status.Success) {
                    uiState.copy(
                        isLoading = false,
                        isIdValidationError = true
                    )
                } else {
                    uiState.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun getAccountValidationStatus() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                isError = false
            )

            val resource = authenticationRepository.getAccountValidationStatus(uiState.email)
            uiState = when (resource.payload) {
                true -> uiState.copy(
                    authenticationStep = AuthenticationUiState.Step.ID_VALIDATION,
                    isLoading = false
                )

                false -> uiState.copy(
                    isLoading = false,
                    isEmailValidationError = true
                )

                null -> uiState.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    fun retry() {
        setEmptyState()

        when (uiState.authenticationStep) {
            AuthenticationUiState.Step.AUTHENTICATION_COMPLETED -> Unit
            AuthenticationUiState.Step.AUTHENTICATION -> authenticate(
                email = uiState.email,
                password = uiState.password
            )

            AuthenticationUiState.Step.EMAIL_VALIDATION -> getAccountValidationStatus()
            AuthenticationUiState.Step.ID_VALIDATION -> uploadImage(
                idImageUri = uiState.idImageUri
            )
        }
    }

    fun setEmptyState() {
        uiState = uiState.copy(
            isLoading = false,
            isError = false,
            isPasswordIncorrectError = false,
            isEmailValidationError = false,
            isIdValidationError = false,
        )
    }

    private suspend fun register() {
        val resource = authenticationRepository.register(uiState.email, uiState.password)
        uiState = when (resource.payload) {
            true -> uiState.copy(
                authenticationStep = AuthenticationUiState.Step.EMAIL_VALIDATION,
                isLoading = false
            )

            else -> uiState.copy(
                isLoading = false,
                isError = true
            )
        }
    }
}
