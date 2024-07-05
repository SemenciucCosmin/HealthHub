package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class ServiceDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Float?,
    @SerializedName("discountedPrice") val discountedPrice: Float?,
    @SerializedName("duration") val duration: Int?,
)
