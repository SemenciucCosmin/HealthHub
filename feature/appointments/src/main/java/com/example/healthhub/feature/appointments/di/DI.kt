package com.example.healthhub.feature.appointments.di

import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureAppointmentsModule = module {
    viewModelOf(::AppointmentsViewModel)
}
