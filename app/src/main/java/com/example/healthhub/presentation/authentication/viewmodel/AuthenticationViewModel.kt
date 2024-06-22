package com.example.healthhub.presentation.authentication.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.data.authentication.repository.AuthenticationRepository
import com.example.healthhub.data.model.Status
import com.example.healthhub.data.preferences.repository.PreferencesRepository
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
                authenticationStatus = Status.Loading
            )

            val resource = authenticationRepository.login(email, password)
            when (val loginStatus = resource.getOrNull()) {
                LoginStatus.EmailError -> register()

                LoginStatus.PasswordError -> {
                    uiState = uiState.copy(authenticationStatus = Status.DataError)
                }

                is LoginStatus.Success -> {
                    uiState = uiState.copy(
                        authenticationStep = AuthenticationUiState.Step.AUTHENTICATION_COMPLETED,
                        authenticationStatus = Status.Success
                    )

                    preferencesRepository.saveUserInformation(
                        id = loginStatus.userId,
                        email = uiState.email,
                        password = uiState.password
                    )
                }

                else -> uiState = uiState.copy(authenticationStatus = Status.NetworkError)
            }
        }
    }

    fun uploadImage(idImageUri: Uri) {
        viewModelScope.launch {
            uiState = uiState.copy(idValidationStatus = Status.Loading)

            val resource = authenticationRepository.uploadID(uiState.email, idImageUri)
            resource.getOrNull()?.let { idValidation ->
                uiState = when {
                    idValidation.updated && idValidation.integrity -> {
                        uiState.copy(
                            authenticationStep = AuthenticationUiState.Step.AUTHENTICATION_COMPLETED,
                            idValidationStatus = Status.Success
                        )
                    }

                    else -> {
                        uiState.copy(idValidationStatus = Status.DataError)
                    }
                }
            } ?: run {
                uiState = uiState.copy(idValidationStatus = Status.NetworkError)
            }
        }
    }

    fun getAccountValidationStatus() {
        viewModelScope.launch {
            uiState = uiState.copy(emailValidationStatus = Status.Loading)
            val resource = authenticationRepository.getAccountValidationStatus(uiState.email)
            uiState = when (resource.getOrNull()) {
                true -> uiState.copy(
                    authenticationStep = AuthenticationUiState.Step.ID_VALIDATION,
                    emailValidationStatus = Status.Success
                )

                false -> uiState.copy(emailValidationStatus = Status.DataError)
                null -> uiState.copy(emailValidationStatus = Status.NetworkError)
            }
        }
    }

    private suspend fun register() {
        val resource = authenticationRepository.register(uiState.email, uiState.password)
        uiState = when (resource.getOrNull()) {
            true -> uiState.copy(
                authenticationStep = AuthenticationUiState.Step.EMAIL_VALIDATION,
                authenticationStatus = Status.Success
            )

            false -> uiState.copy(authenticationStatus = Status.DataError)
            null -> uiState.copy(authenticationStatus = Status.NetworkError)
        }
    }
}
