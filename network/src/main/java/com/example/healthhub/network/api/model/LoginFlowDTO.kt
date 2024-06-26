package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class LoginFlowDTO(
    @SerializedName("loginFlow") val innerLoginFlowDTO: InnerLoginFlowDTO?,
)

data class InnerLoginFlowDTO(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("developerReason") val developerReason: String?,
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("status") val status: String?,
)
