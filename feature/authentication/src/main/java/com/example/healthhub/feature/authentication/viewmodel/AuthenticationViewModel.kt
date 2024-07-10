package com.example.healthhub.feature.authentication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.data.authentication.repository.AuthenticationRepository
import com.example.healthhub.domain.account.SetUserInformationUseCase
import com.example.healthhub.feature.authentication.viewmodel.model.AuthenticationUiState
import kotlinx.coroutines.launch
import java.io.File

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val setUserInformationUseCase: SetUserInformationUseCase
) : ViewModel() {

    var uiState by mutableStateOf(AuthenticationUiState())
        private set

    fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(
                email = email,
                password = password,
                isLoading = true,
                isError = false,
                showToast = false
            )

            val resource = authenticationRepository.login(email, password)
            when (val loginStatus = resource.payload) {
                LoginStatus.EmailError -> register()

                LoginStatus.PasswordError -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isPasswordIncorrectError = true,
                        showToast = false
                    )
                }

                is LoginStatus.Success -> {
                    setUserInformationUseCase(loginStatus.userId)
                    uiState = uiState.copy(id = loginStatus.userId)
                }

                else -> uiState = uiState.copy(
                    isLoading = false,
                    isError = true,
                    showToast = false
                )
            }
        }
    }

    fun uploadImage(imageFile: File) {
        viewModelScope.launch {
            uiState = uiState.copy(
                imageFile = imageFile,
                isLoading = true,
                isError = false
            )

            val resource = authenticationRepository.uploadID(uiState.email, imageFile)
            resource.payload?.let { idValidation ->
                if (idValidation.updated && idValidation.integrity) {
                    setUserInformationUseCase(uiState.id)
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        isIdValidationError = true
                    )
                }
            } ?: run {
                uiState = uiState.copy(
                    isLoading = false,
                    isIdValidationError = true
                )
            }
        }
    }

    fun getAccountValidationStatus() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                isError = false,
                showToast = false
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
            AuthenticationUiState.Step.AUTHENTICATION -> authenticate(
                email = uiState.email,
                password = uiState.password
            )

            AuthenticationUiState.Step.EMAIL_VALIDATION -> getAccountValidationStatus()
            AuthenticationUiState.Step.ID_VALIDATION -> uiState.imageFile?.let {
                uploadImage(imageFile = it)
            }
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
        uiState = when (val userId = resource.payload?.userId) {
            null -> uiState.copy(
                isLoading = false,
                isError = true
            )

            else -> uiState.copy(
                authenticationStep = AuthenticationUiState.Step.EMAIL_VALIDATION,
                id = userId,
                isLoading = false,
                showToast = true
            )
        }
    }
}
