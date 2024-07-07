package com.example.healthhub.data.home.repository

import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.network.api.service.SubscriptionsApi
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status

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
                period = dto.subscriptionAgeMonths ?: return@mapNotNull null,
                specializationId = dto.specializationId ?: return@mapNotNull null
            )
        }

        return Resource(subscriptions, resource.status)
    }

    override suspend fun getSubscriptionDetails(
        userId: Int,
        subscriptionId: Int,
        specializationId: Int
    ): Resource<SubscriptionDetails> {
        val subscriptionsResource = getSubscriptions(userId)
        val subscriptions = subscriptionsResource.payload ?: emptyList()
        val subscriptionDetailsResource = subscriptionsApi.getUserSubscriptionDetails(
            mapOf(
                "specializationId" to specializationId,
                "userId" to userId
            )
        )

        val emptyResource = Resource<SubscriptionDetails>(null, Status.ResourceNotFoundError)
        val subscriptionDetailsDTO = subscriptionDetailsResource.payload?.inner?.subscriptionModel
        val services = subscriptionDetailsDTO?.services?.mapNotNull {
            Service(
                id = it.id ?: return@mapNotNull null,
                name = it.name ?: return@mapNotNull null,
                description = it.description ?: return@mapNotNull null,
                price = it.price ?: return@mapNotNull null,
                discountedPrice = it.discountedPrice ?: return@mapNotNull null,
                duration = it.duration ?: return@mapNotNull null,
            )
        } ?: emptyList()

        val subscription = subscriptions.firstOrNull {
            it.id == subscriptionId
        } ?: return emptyResource

        val subscriptionDetails = SubscriptionDetails(
            id = subscription.id,
            name = subscription.name,
            active = subscription.active,
            pricePerMonth = subscription.pricePerMonth,
            period = subscription.period,
            specializationId = subscriptionDetailsDTO?.id ?: return emptyResource,
            specializationName = subscriptionDetailsDTO.name,
            specializationDescription = subscriptionDetailsDTO.description,
            services = services,
        )

        return Resource(subscriptionDetails, subscriptionDetailsResource.status)
    }
}
