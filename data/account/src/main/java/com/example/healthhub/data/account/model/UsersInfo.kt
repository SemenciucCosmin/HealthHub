package com.example.healthhub.data.account.model

data class UsersInfo(
    val parent: User,
    val child: User?,
    val selectedUserId: Int,
)
