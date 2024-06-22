package com.example.healthhub.network.api.model

import com.squareup.moshi.Json

data class RegisterFlowDTO(
    @field:Json(name = "status") val status: String,
)
