package com.example.healthhub.feature.medicalfile.viewmodel.model

import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Specialization

data class MedicalFileUiState(
    val medics: List<Medic> = emptyList(),
    val filteredMedics: List<Medic> = emptyList(),
    val specializations: List<Specialization> = emptyList(),
    val pastAppointments: List<Appointment> = emptyList(),
    val selectedSpecializationId: Int? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
