package com.example.healthhub.data.authentication.model

sealed class LoginStatus {
    data object EmailError : LoginStatus()
    data object PasswordError : LoginStatus()
    data class Success(val userId: Int) : LoginStatus()
}
