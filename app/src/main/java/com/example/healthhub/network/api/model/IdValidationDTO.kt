package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class IdValidationDTO(
    @SerializedName("processIdCardImageFlow") val innerIdValidationDTO: InnerIdValidationDTO?,
)

data class InnerIdValidationDTO(
    @SerializedName("updated") val updated: Boolean?,
    @SerializedName("integrity") val integrity: Boolean?,
    @SerializedName("status") val status: String?,
)
