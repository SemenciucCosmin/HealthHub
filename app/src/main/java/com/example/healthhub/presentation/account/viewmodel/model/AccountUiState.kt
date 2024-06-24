package com.example.healthhub.presentation.account.viewmodel.model

import com.example.healthhub.presentation.account.Account

data class AccountUiState(
    val parentAccount: Account,
    val childAccount: Account?,
)

