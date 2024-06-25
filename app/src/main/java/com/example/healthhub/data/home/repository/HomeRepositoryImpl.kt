package com.example.healthhub.data.home.repository

import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.network.api.service.SubscriptionsApi
import com.example.healthhub.network.resource.Resource

class HomeRepositoryImpl(
    private val subscriptionsApi: SubscriptionsApi
) : HomeRepository {
    override suspend fun getSubscriptions(userId: Int): Resource<List<Subscription>> {
        val resource = subscriptionsApi.getUserSubscriptions(userId)
        val subscriptionDTOs = resource.payload?.innerSubscriptionsDTO?.subscriptions ?: emptyList()
        val subscriptions = subscriptionDTOs.mapNotNull { dto ->
            Subscription(
                id = dto.id ?: return@mapNotNull null,
                name = dto.name ?: return@mapNotNull null,
                active = dto.active ?: return@mapNotNull null,
                pricePerMonth = dto.pricePerMonth ?: return@mapNotNull null,
                subscriptionAgeMonths = dto.subscriptionAgeMonths ?: return@mapNotNull null,
                specializationId = dto.specializationId ?: return@mapNotNull null
            )
        }

        return Resource(subscriptions, resource.status)
    }
}
