package com.example.healthhub.data.home.repository

import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.network.resource.Resource

interface HomeRepository {
    suspend fun getSubscriptions(userId: Int): Resource<List<Subscription>>

    suspend fun getSubscriptionDetails(
        userId: Int,
        subscriptionId: Int,
        specializationId: Int
    ): Resource<SubscriptionDetails>
}
