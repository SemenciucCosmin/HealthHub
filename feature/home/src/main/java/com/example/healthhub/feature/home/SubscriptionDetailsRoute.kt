package com.example.healthhub.feature.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.home.viewmodel.SubscriptionDetailsViewModel
import com.example.healthhub.network.resource.Status
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SubscriptionDetailsRoute(
    subscriptionId: Int,
    specializationId: Int,
    isUserSubscription: Boolean,
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<SubscriptionDetailsViewModel>()
    val subscriptionDetails = viewModel.uiState.subscriptionDetails
    val navController = LocalNavController.current

    LaunchedEffect(specializationId) {
        viewModel.loadSubscriptionDetails(subscriptionId, specializationId)
    }

    when (viewModel.uiState.subscriptionAdditionStatus) {
        Status.Empty, Status.Loading -> Unit
        Status.Success -> {
            val subscriptionSuccessMessage = stringResource(R.string.lbl_subscription_add_success)
            Toast.makeText(context, subscriptionSuccessMessage, Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }

        else -> {
            val subscriptionErrorMessage = stringResource(R.string.lbl_subscription_add_error)
            Toast.makeText(context, subscriptionErrorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    when {
        viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())
        viewModel.uiState.isError || subscriptionDetails == null -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = { viewModel.retry(subscriptionId, specializationId) }
        )

        viewModel.uiState.subscriptionAdditionStatus is Status.Loading -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    32.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    strokeWidth = 3.dp
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.lbl_subscription_add_loading),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        else -> SubscriptionDetailsScreen(
            subscriptionDetails = subscriptionDetails,
            isUserSubscription = isUserSubscription,
            modifier = Modifier.padding(16.dp),
            onAddSubscriptionClick = { viewModel.addSubscription(subscriptionId) }
        )
    }
}
