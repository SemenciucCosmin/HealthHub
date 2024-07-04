package com.example.healthhub.network.api.model

data class AppointmentRequest(
    private val availableAppointmentId: Int,
    private val userId: Int,
    private val doctorId: Int,
    private val countyId: Int,
    private val locationId: String,
    private val stateId: String?,
    private val appointmentStartDate: Long,
    private val appointmentDuration: Int?,
    private val price: Float?,
    private val specializationId: Int,
    private val specializations: List<SpecializationRequest>,
)

data class SpecializationRequest(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val services: List<ServiceRequest>
)

data class ServiceRequest(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val price: Float,
    private val duration: Int,
)
