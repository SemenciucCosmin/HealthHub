package com.example.healthhub.presentation.authentication.viewmodel.model

import com.example.healthhub.data.model.Status
import com.example.healthhub.data.util.BLANK

data class AuthenticationUiState(
    val email: String = String.BLANK,
    val password: String = String.BLANK,
    val emailValidationStatus: Status = Status.None,
    val idValidationStatus: Status = Status.None,
    val authenticationStatus: Status = Status.None,
    val authenticationStep: AuthenticationStep = AuthenticationStep.AUTHENTICATION,
)
