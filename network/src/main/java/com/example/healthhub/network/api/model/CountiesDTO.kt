package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class CountiesDTO(
    @SerializedName("getAllCounties") val innerCountiesDTO: InnerCountiesDTO?
)

data class InnerCountiesDTO(
    @SerializedName("countyEntities") val entities: List<CountyDTO>?
)

data class CountyDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
)
