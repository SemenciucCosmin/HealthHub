package com.example.healthhub.network.api.model

import com.squareup.moshi.Json

data class LoginFlowDTO(
    @field:Json(name = "userId") val userId: Int?,
    @field:Json(name = "developerReason") val developerReason: String?,
    @field:Json(name = "statusCode") val statusCode: String?,
    @field:Json(name = "status") val status: String?,
)
