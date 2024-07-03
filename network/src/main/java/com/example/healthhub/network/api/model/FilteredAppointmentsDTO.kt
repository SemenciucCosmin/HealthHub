package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class FilteredAppointmentsDTO(
    @SerializedName("getFilteredAppointments") val innerFilteredAppointments: InnerFilteredAppointmentsDTO?
)

data class InnerFilteredAppointmentsDTO(
    @SerializedName("filteredAppointments") val entities: List<FilteredAppointmentDTO>?
)

data class FilteredAppointmentDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("doctorFullName") val doctorFullName: String?,
    @SerializedName("doctorId") val doctorId: Int?,
    @SerializedName("ranking") val ranking: Int?,
    @SerializedName("specializationAndServicesModel") val details: FilteredAppointmentDetailsDTO?,
    @SerializedName("locationEntity") val locationEntity: FilteredAppointmentLocationDTO?,
    @SerializedName("date") val dateMillis: Long?,
)

data class FilteredAppointmentDetailsDTO(
    @SerializedName("specializationId") val specializationId: Int?,
    @SerializedName("specializationName") val specializationName: String?,
    @SerializedName("services") val services: List<ServiceDTO>?,
)

data class FilteredAppointmentLocationDTO(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("county") val county: CountyDTO?,
)
