package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class AccountRegistrationDTO(
    @SerializedName("getAccountValidationStatus")
    val innerAccountRegistrationDTO: InnerAccountRegistrationDTO?,
)

data class InnerAccountRegistrationDTO(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("validation") val validation: Boolean?,
    @SerializedName("status") val status: String?,
)
