package com.example.healthhub.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.home.viewmodel.HomeViewModel
import com.example.healthhub.ui.catalog.R
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
            title = stringResource(R.string.lbl_user_subscriptions_title),
            emptyMessage = stringResource(R.string.lbl_no_user_subscriptions_message),
            subscriptions = viewModel.uiState.userSubscriptions,
            isUserSection = true,
            onSubscriptionClick = { subscriptionId, specializationId ->
                navController.navigate(
                    NavDestination.SubscriptionDetails(
                        subscriptionId = subscriptionId,
                        specializationId = specializationId,
                        isUserSubscription = true
                    )
                )
            }
        )

        SubscriptionsSection(
            title = stringResource(R.string.lbl_all_subscriptions_title),
            emptyMessage = stringResource(R.string.lbl_no_subscriptions_message),
            subscriptions = viewModel.uiState.allSubscriptions,
            isUserSection = false,
            onSubscriptionClick = { subscriptionId, specializationId ->
                navController.navigate(
                    NavDestination.SubscriptionDetails(
                        subscriptionId = subscriptionId,
                        specializationId = specializationId,
                        isUserSubscription = false
                    )
                )
            }
        )

        NavigationButtonsGrid()
    }
}
