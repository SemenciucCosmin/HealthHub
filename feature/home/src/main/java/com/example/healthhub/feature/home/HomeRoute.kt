package com.example.healthhub.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.home.viewmodel.HomeViewModel
import com.example.healthhub.ui.navigation.components.NavigationButtonsGrid
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute() {
    val viewModel = koinViewModel<HomeViewModel>()
    val navController = LocalNavController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SubscriptionsSection(
            subscriptions = viewModel.uiState.subscriptions,
            onSubscriptionClick = { subscriptionId, specializationId ->
                navController.navigate(
                    NavDestination.SubscriptionDetails(
                        subscriptionId = subscriptionId,
                        specializationId = specializationId
                    )
                )
            }
        )

        NavigationButtonsGrid()
    }
}
