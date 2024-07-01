package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class MedicsDTO(
    @SerializedName("getAllMedics") val innerMedicsDTO: InnerMedicsDTO?
)

data class InnerMedicsDTO(
    @SerializedName("doctorEntityList") val entities: List<MedicDTO>?
)

data class MedicDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("ranking") val ranking: Float?,
    @SerializedName("specializations") val specializations: List<SpecializationDTO>?,
    @SerializedName("services") val services: List<ServiceDTO>?,
    @SerializedName("locations") val locations: List<LocationDTO>?,
    @SerializedName("county") val county: CountyDTO?,
)
