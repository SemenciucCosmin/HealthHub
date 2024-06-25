package com.example.healthhub.presentation.home.viewmodel.model

import com.example.healthhub.data.home.model.Subscription

data class HomeUiState(
    val subscriptions: List<Subscription> = emptyList()
)
