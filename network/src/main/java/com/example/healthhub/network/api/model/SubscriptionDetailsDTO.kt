package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class SubscriptionDetailsDTO(
    @SerializedName("getSpecializationsAndServicesInfo") val inner: InnerSubscriptionDetailsDTO?,
)

data class InnerSubscriptionDetailsDTO(
    @SerializedName("specializationModel") val subscriptionModel: SubscriptionDetailsModelDTO?,
)

data class SubscriptionDetailsModelDTO(
    @SerializedName("specializationId") val id: Int,
    @SerializedName("specializationDescription") val description: String,
    @SerializedName("specializationName") val name: String,
    @SerializedName("serviceModelList") val services: List<ServiceDTO>,
)
