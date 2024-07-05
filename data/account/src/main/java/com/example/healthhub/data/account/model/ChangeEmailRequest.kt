package com.example.healthhub.data.account.model

data class ChangeEmailRequest(
    val appUserEntity: AppUserEntity,
    val email: String
)

data class AppUserEntity(val id: Int)