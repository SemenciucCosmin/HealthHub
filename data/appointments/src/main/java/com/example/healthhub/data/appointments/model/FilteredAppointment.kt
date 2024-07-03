package com.example.healthhub.data.appointments.model

import com.example.healthhub.data.info.model.Location

data class FilteredAppointment(
    val id: Int,
    val doctorFullName: String,
    val doctorId: Int,
    val ranking: Int,
    val specializationId: Int,
    val specializationName: String,
    val services: List<Service>,
    val location: Location,
    val county: County,
    val dateMillis: Long,
)
