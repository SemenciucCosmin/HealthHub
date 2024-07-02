package com.example.healthhub.data.appointments.di

import com.example.healthhub.data.appointments.repository.AppointmentsRepository
import com.example.healthhub.data.appointments.repository.AppointmentsRepositoryImpl
import org.koin.dsl.module

val dataAppointmentsModule = module {
    factory<AppointmentsRepository> {
        AppointmentsRepositoryImpl(get(), get(), get(), get(), get())
    }
}
