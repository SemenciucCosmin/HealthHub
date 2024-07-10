package com.example.healthhub.feature.home.viewmodel.model

import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.network.resource.Status

data class SubscriptionDetailsUiState(
    val subscriptionDetails: SubscriptionDetails? = null,
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val subscriptionAdditionStatus: Status = Status.Empty
)
