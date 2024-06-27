package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class UserInformationDTO(
    @SerializedName("getDataById") val innerUserInformationDTO: InnerUserInformationDTO?,
)

data class InnerUserInformationDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("cnp") val cnp: String?,
    @SerializedName("series") val series: String?,
    @SerializedName("lastname") val lastname: String?,
    @SerializedName("firstname") val firstname: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("dob") val dateOfBirth: String?,
    @SerializedName("sex") val sex: String?,
)
