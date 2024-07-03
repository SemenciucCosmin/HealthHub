package com.example.healthhub.data.appointments.repository

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.network.resource.Resource

interface AppointmentsRepository {
    suspend fun getAppointments(
        usersInfo: UsersInfo,
        timeframe: AppointmentTimeframe,
    ): Resource<List<Appointment>>

    suspend fun getSpecializations(): Resource<List<Specialization>>

    suspend fun getMedics(): Resource<List<Medic>>

    suspend fun getCounties(): Resource<List<County>>

    suspend fun filterAppointments(
        specializationName: String,
        countyName: String,
        startDateMillis: Long
    ): Resource<List<Appointment>>
}