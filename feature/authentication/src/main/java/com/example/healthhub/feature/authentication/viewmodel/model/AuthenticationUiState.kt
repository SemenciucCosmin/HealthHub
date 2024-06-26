package com.example.healthhub.feature.authentication.viewmodel.model

import com.example.healthhub.data.util.BLANK
import java.io.File

data class AuthenticationUiState(
    val id: Int = 0,
    val email: String = String.BLANK,
    val password: String = String.BLANK,
    val imageFile: File? = null,
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
