package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class BirthCertificateValidationDTO(
    @SerializedName("validateBirthCertificate")
    val innerBirthCertificateValidationDTO: InnerBirthCertificateValidationDTO?,
)

data class InnerBirthCertificateValidationDTO(
    @SerializedName("integrity") val integrity: Boolean?,
    @SerializedName("status") val status: String?,
)
