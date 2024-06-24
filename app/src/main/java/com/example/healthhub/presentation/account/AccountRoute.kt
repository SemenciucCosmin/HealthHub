package com.example.healthhub.presentation.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthhub.presentation.account.viewmodel.AccountScreen
import com.example.healthhub.presentation.account.viewmodel.AccountViewModel
import com.example.healthhub.presentation.ui.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountRoute() {
    val viewModel = koinViewModel<AccountViewModel>()
    when (val uiState = viewModel.uiState) {
        null -> LoadingScreen(modifier = Modifier.fillMaxSize())
        else -> AccountScreen(
            parentAccount = uiState.parentAccount,
            childAccount = uiState.childAccount,
            onSelectAccountClick = viewModel::selectAccount,
            onAddAccountClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
