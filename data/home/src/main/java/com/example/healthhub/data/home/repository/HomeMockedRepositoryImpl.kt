package com.example.healthhub.data.home.repository

import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status

class HomeMockedRepositoryImpl : HomeRepository {

    override suspend fun getSubscriptions(userId: Int): Resource<List<Subscription>> {
        return Resource(
            status = Status.Success,
            payload = getMockedSubscriptions()
        )
    }

    override suspend fun getSubscriptionDetails(
        userId: Int,
        subscriptionId: Int,
        specializationId: Int
    ): Resource<SubscriptionDetails> {
        return Resource(
            status = Status.Success,
            payload = SubscriptionDetails(
                id = 3525,
                name = "Henrietta Richard",
                active = false,
                pricePerMonth = 16.17,
                period = 4835,
                specializationId = 1,
                specializationDescription = "description",
                specializationName = "name",
                services = getMockedServices(),
            )
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

    private fun getMockedServices() = List(5) { serviceIndex ->
        Service(
            id = serviceIndex,
            name = "Service $serviceIndex",
            description = "$serviceIndex",
            price = serviceIndex.toFloat(),
            discountedPrice = serviceIndex.toFloat(),
            duration = serviceIndex
        )
    }
}
