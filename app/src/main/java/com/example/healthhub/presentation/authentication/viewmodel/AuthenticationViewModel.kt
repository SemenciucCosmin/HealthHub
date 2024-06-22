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
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    var uiState by mutableStateOf(AuthenticationUiState())
        private set

    fun authenticate(email: String, password: String) {
        uiState = uiState.copy(
            email = email,
            password = password,
            authenticationStatus = Status.Loading
        )

        viewModelScope.launch {
            val resource = authenticationRepository.login(email, password)
            when (val loginStatus = resource.getOrNull()) {
                LoginStatus.EmailError -> {
                    uiState = uiState.copy(
                        authenticationStep = AuthenticationUiState.Step.EMAIL_VALIDATION,
                        authenticationStatus = Status.Success
                    )
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

                else -> uiState = uiState.copy(authenticationStatus = Status.Error)
            }
        }
    }

    fun uploadImage(idImageUri: Uri) {
        uiState = uiState.copy(idImageUri = idImageUri)
    }
}
