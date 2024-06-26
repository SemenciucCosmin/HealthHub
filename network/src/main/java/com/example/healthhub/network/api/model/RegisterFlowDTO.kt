package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class RegisterFlowDTO(
    @SerializedName("registerFlow") val innerRegisterFlowDTO: InnerRegisterFlowDTO?,
)

data class InnerRegisterFlowDTO(
    @SerializedName("status") val status: String?,
)
