package com.example.healthhub.feature.appointments.viewmodel.model

import com.example.healthhub.data.appointments.model.Appointment

data class AppointmentsUiState(
    val pastAppointments: List<Appointment> = emptyList(),
    val futureAppointments: List<Appointment> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
