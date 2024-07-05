package com.example.healthhub.data.account.model

data class ChangePasswordRequest(
    val userId: Int,
    val newPassword: String
)
