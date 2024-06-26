package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class LocationsDTO(
    @SerializedName("getAllLocations") val innerLocationsDTO: InnerLocationsDTO?,
)

data class InnerLocationsDTO(
    @SerializedName("locationEntities") val locationEntities: List<LocationDTO>?,
)

data class LocationDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
)
