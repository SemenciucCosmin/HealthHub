package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class ChildMedicsDTO(
    @SerializedName("getMedicsByChildId") val innerMedicsDTO: InnerMedicsDTO?
)
