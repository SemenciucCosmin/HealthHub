package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class SpecializationsDTO(
    @SerializedName("getAllSpecializations") val innerSpecializationsDTO: InnerSpecializationsDTO?
)

data class InnerSpecializationsDTO(
    @SerializedName("specializationEntityList") val entities: List<SpecializationDTO>?
)

data class SpecializationDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("services") val services: List<ServiceDTO>?,
)
