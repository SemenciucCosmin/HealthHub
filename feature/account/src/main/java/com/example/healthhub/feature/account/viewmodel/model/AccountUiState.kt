package com.example.healthhub.feature.account.viewmodel.model

import java.io.File

data class AccountUiState(
    val parentAccount: Account,
    val childAccount: Account? = null,
    val imageFile: File? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)
