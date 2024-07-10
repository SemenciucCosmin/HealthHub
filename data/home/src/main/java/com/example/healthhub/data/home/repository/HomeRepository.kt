package com.example.healthhub.data.home.repository

import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.network.resource.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getUserSubscriptions(userId: Int): Flow<Resource<List<Subscription>>>

    suspend fun getAllSubscriptions(): Resource<List<Subscription>>

    suspend fun addSubscription(
        userId: Int,
        subscriptionId: Int,
        validFromDateMillis: Long
    ): Resource<Unit>

    suspend fun getSubscriptionDetails(
        userId: Int,
        subscriptionId: Int,
        specializationId: Int
    ): Resource<SubscriptionDetails>
}
