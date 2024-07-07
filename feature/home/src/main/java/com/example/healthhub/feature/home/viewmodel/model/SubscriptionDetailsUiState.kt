package com.example.healthhub.feature.home.viewmodel.model

import com.example.healthhub.data.home.model.SubscriptionDetails

data class SubscriptionDetailsUiState(
    val subscriptionDetails: SubscriptionDetails? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = true,
)
