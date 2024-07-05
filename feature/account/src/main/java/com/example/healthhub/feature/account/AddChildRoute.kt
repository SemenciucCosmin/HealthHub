package com.example.healthhub.feature.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.healthhub.feature.account.viewmodel.AccountViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddChildRoute() {
    val navController = LocalNavController.current
    val viewModel = koinViewModel<AccountViewModel>()
    val uiState = viewModel.uiState

    when {
        uiState == null || uiState.isLoading -> LoadingScreen(
            modifier = Modifier.fillMaxSize()
        )

        uiState.isError -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = viewModel::retry,
        )

        uiState.isSuccess -> navController.navigate(NavDestination.Home) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }

        else -> {
            AddChildScreen(
                modifier = Modifier.fillMaxSize(),
                onImageFileReady = viewModel::uploadImage
            )
        }
    }
}
