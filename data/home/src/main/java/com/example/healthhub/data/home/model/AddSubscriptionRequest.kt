package com.example.healthhub.data.home.model

data class AddSubscriptionRequest(
    val userId: Int,
    val subscriptionId: Int,
    val validFrom: Long
)
