package com.example.healthhub.feature.medicalfile.viewmodel.model

import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.Medic

data class MedicalFileUiState(
    val medics: List<Medic> = emptyList(),
    val pastAppointments: List<Appointment> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
