package com.example.healthhub.network.api.model

import com.squareup.moshi.Json

data class IdValidationDTO(
    @field:Json(name = "updated") val updated: Boolean?,
    @field:Json(name = "integrity") val integrity: Boolean?,
    @field:Json(name = "status") val status: String?,
)
