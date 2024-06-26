package com.example.healthhub.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute() {
    val viewModel = koinViewModel<HomeViewModel>()

    Column(modifier = Modifier.fillMaxSize()) {
        SubscriptionsSection(
            modifier = Modifier.padding(vertical = 16.dp),
            subscriptions = viewModel.uiState.subscriptions
        )
    }
}
