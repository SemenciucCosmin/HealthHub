package com.example.healthhub.presentation.authentication.viewmodel.model

import android.net.Uri
import com.example.healthhub.data.util.BLANK

data class AuthenticationUiState(
    val email: String = String.BLANK,
    val password: String = String.BLANK,
    val idImageUri: Uri = Uri.EMPTY,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isPasswordIncorrectError: Boolean = false,
    val isEmailValidationError: Boolean = false,
    val isIdValidationError: Boolean = false,
    val authenticationStep: Step = Step.AUTHENTICATION,
) {
    enum class Step {
        AUTHENTICATION,
        AUTHENTICATION_COMPLETED,
        EMAIL_VALIDATION,
        ID_VALIDATION,
    }


}
