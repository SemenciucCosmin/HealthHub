package com.example.healthhub.feature.home.viewmodel.model

import com.example.healthhub.data.home.model.Subscription

data class HomeUiState(
    val userSubscriptions: List<Subscription> = emptyList(),
    val allSubscriptions: List<Subscription> = emptyList(),
)
