package com.example.healthhub.network.api.model

import com.squareup.moshi.Json

data class AccountRegistrationDTO(
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "validation") val validation: Boolean,
    @field:Json(name = "status") val status: String,
)
