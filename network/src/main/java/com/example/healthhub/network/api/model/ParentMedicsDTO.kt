package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class ParentMedicsDTO(
    @SerializedName("getMedicsByUserId") val innerMedicsDTO: InnerMedicsDTO?
)
