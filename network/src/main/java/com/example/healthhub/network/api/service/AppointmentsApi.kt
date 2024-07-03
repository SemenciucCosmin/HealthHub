package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.AppointmentsDTO
import com.example.healthhub.network.api.model.FilterAppointmentsDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppointmentsApi {
    @GET("/api/v1/medicalclinicproject/appointments/getActiveAppointmentsByUserId/{parentId}")
    suspend fun getParentFutureAppointments(
        @Path("parentId") parentId: Int
    ): Resource<AppointmentsDTO>

    @GET("/api/v1/medicalclinicproject/appointments/getPastAppointmentsByUserId/{parentId}")
    suspend fun getParentPastAppointments(
        @Path("parentId") parentId: Int
    ): Resource<AppointmentsDTO>

    @GET("/api/v1/medicalclinicproject/appointments/getActiveAppointmentsByUserIdAndChildId/{parentId}/{childId}")
    suspend fun getChildFutureAppointments(
        @Path("parentId") parentId: Int,
        @Path("childId") childId: Int,
    ): Resource<AppointmentsDTO>

    @GET("/api/v1/medicalclinicproject/appointments/getPastAppointmentsByUserIdAndChildId/{parentId}/{childId}")
    suspend fun getChildPastAppointments(
        @Path("parentId") parentId: Int,
        @Path("childId") childId: Int,
    ): Resource<AppointmentsDTO>

    @GET("/api/v1/medicalclinicproject/appointments/filter")
    suspend fun filterAppointments(
        @Query("specialization") specializationName: String,
        @Query("county") countyName: String,
        @Query("startDate") startDateMillis: Long,
    ): Resource<FilterAppointmentsDTO>
}
