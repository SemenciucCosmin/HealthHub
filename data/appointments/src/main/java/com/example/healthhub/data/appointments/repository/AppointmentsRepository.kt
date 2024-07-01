package com.example.healthhub.data.appointments.repository

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.network.resource.Resource

interface AppointmentsRepository {
    suspend fun getAppointments(
        usersInfo: UsersInfo,
        timeframe: AppointmentTimeframe,
    ): Resource<List<Appointment>>
}