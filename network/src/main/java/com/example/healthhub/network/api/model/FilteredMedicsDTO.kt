package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class FilteredMedicsDTO(
    @SerializedName("getAllMedicsBySpecializationAndCounty") val innerFilteredMedics: InnerFilteredMedicsDTO?
)

data class InnerFilteredMedicsDTO(
    @SerializedName("doctorEntityList") val entities: List<MedicDTO>?
)
