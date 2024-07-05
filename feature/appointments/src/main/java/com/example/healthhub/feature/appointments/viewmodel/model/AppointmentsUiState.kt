package com.example.healthhub.feature.appointments.viewmodel.model

import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.FilteredAppointment
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.data.info.model.Location

data class AppointmentsUiState(
    val pastAppointments: List<Appointment> = emptyList(),
    val futureAppointments: List<Appointment> = emptyList(),
    val filteredAppointments: List<FilteredAppointment> = emptyList(),
    val filteredAppointment: FilteredAppointment? = null,
    val specializations: List<Specialization> = emptyList(),
    val filteredMedics: List<Medic> = emptyList(),
    val selectedServices: List<Service> = emptyList(),
    val selectedSpecializationId: Int? = null,
    val selectedCountyId: Int? = null,
    val selectedMedicId: Int? = null,
    val selectedLocationId: Int? = null,
    val selectedStartDateMillis: Long = 0,
    val totalPrice: Float = 0f,
    val totalDuration: Int = 0,
    val counties: List<County> = emptyList(),
    val medics: List<Medic> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
