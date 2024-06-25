package com.example.healthhub.presentation.account.viewmodel.model

import com.example.healthhub.data.account.model.User

data class AccountUiState(
    val parentAccount: Account,
    val childAccount: Account?,
    val selectedUser: User,
)

