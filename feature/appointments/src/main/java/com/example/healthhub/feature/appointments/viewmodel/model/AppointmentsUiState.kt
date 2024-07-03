package com.example.healthhub.feature.appointments.viewmodel.model

import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.FilteredAppointment
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Specialization

data class AppointmentsUiState(
    val pastAppointments: List<Appointment> = emptyList(),
    val futureAppointments: List<Appointment> = emptyList(),
    val filteredAppointments: List<FilteredAppointment> = emptyList(),
    val specializations: List<Specialization> = emptyList(),
    val counties: List<County> = emptyList(),
    val medics: List<Medic> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
