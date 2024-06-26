package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class SubscriptionsDTO(
    @SerializedName("getSubscriptionsByUserId") val innerSubscriptionsDTO: InnerSubscriptionsDTO?,
)

data class InnerSubscriptionsDTO(
    @SerializedName("subscriptionEntities") val subscriptions: List<SubscriptionDTO>?,
)

data class SubscriptionDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("active") val active: Boolean?,
    @SerializedName("pricePerMonth") val pricePerMonth: Double?,
    @SerializedName("subscriptionAgeMonths") val subscriptionAgeMonths: Int?,
    @SerializedName("specializationId") val specializationId: Int?,
)
