package com.example.healthhub.network.api.model

import com.google.gson.annotations.SerializedName

data class AppointmentsDTO(
    @SerializedName("getActiveAppointmentsByUserId") val innerAppointmentsDTO: InnerAppointmentsDTO?
)

data class FilterAppointmentsDTO(
    @SerializedName("getActiveAppointmentsByUserId") val innerFilterAppointments: InnerFilterAppointmentsDTO?
)

data class InnerAppointmentsDTO(
    @SerializedName("appointmentEntityList") val entities: List<AppointmentDTO>?
)

data class InnerFilterAppointmentsDTO(
    @SerializedName("appointmentEntityList") val entities: List<AppointmentDTO>?
)

data class AppointmentDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("availableAppointmentId") val availableAppointmentId: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("doctorId") val doctorId: Int?,
    @SerializedName("countyId") val countyId: Int?,
    @SerializedName("locationId") val locationId: Int?,
    @SerializedName("state") val state: String?,
    @SerializedName("appointmentStartDate") val startDate: String?,
    @SerializedName("appointmentDuration") val duration: Int?,
    @SerializedName("price") val price: Float?,
    @SerializedName("specializationId") val specializationId: Int?,
    @SerializedName("childId") val childId: Int?,
    @SerializedName("specializations") val specializations: List<SpecializationDTO>?,
)
