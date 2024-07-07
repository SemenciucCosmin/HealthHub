package com.example.healthhub.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.home.viewmodel.SubscriptionDetailsViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SubscriptionDetailsRoute(subscriptionId: Int, specializationId: Int) {
    val viewModel = koinViewModel<SubscriptionDetailsViewModel>()
    val subscriptionDetails =  viewModel.uiState.subscriptionDetails

    LaunchedEffect(specializationId) {
        viewModel.loadSubscriptionDetails(subscriptionId, specializationId)
    }

    when {
        viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())
        viewModel.uiState.isError || subscriptionDetails == null -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = { viewModel.retry(subscriptionId, specializationId) }
        )

        else -> SubscriptionDetailsScreen(
            subscriptionDetails = subscriptionDetails,
            modifier = Modifier.padding(16.dp)
        )
    }
}
