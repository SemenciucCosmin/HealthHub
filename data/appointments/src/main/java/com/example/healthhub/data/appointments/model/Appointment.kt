package com.example.healthhub.data.appointments.model

import com.example.healthhub.data.info.model.Location

data class Appointment(
    val id: Int,
    val availableAppointmentId: Int,
    val userId: Int,
    val medic: Medic,
    val county: County,
    val location: Location,
    val state: String,
    val startDate: Long,
    val duration: Int,
    val price: Float,
    val specialization: Specialization,
    val childId: Int?,
    val specializations: List<Specialization>,
    val isCancelable: Boolean
)
