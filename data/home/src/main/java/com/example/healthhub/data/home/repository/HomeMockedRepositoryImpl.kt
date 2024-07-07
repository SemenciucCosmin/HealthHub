package com.example.healthhub.data.home.repository

import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status

class HomeMockedRepositoryImpl : HomeRepository {

    override suspend fun getSubscriptions(userId: Int): Resource<List<Subscription>> {
        return Resource(
            status = Status.Success,
            payload = getMockedSubscriptions()
        )
    }

    private fun getMockedSubscriptions() = List(5) { subscriptionIndex ->
        Subscription(
            id = subscriptionIndex,
            name = "Subscription $subscriptionIndex",
            active = subscriptionIndex % 2 == 0,
            pricePerMonth = subscriptionIndex.toDouble(),
            period = subscriptionIndex,
            specializationId = subscriptionIndex
        )
    }
}
