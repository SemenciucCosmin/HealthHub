package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class UserChildInformationDTO(
    @SerializedName("getUserChild") val innerUserChildInformationDTO: InnerUserChildInformationDTO?,
)

data class InnerUserChildInformationDTO(
    @SerializedName("childEntities") val entities: List<UserChildDTO>?,
)

data class UserChildDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("surname") val surname: String?,
    @SerializedName("firstName") val firstname: String?,
    @SerializedName("childCnp") val cnp: String?,
    @SerializedName("dob") val dateOfBirth: String?,
    @SerializedName("fatherSurname") val fatherSurname: String?,
    @SerializedName("fatherFirstName") val fatherFirstname: String?,
    @SerializedName("motherSurname") val motherSurname: String?,
    @SerializedName("motherFirstName") val motherFirstName: String?,
)
