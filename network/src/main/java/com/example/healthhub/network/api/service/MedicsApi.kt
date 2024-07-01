package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.MedicsDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET

interface MedicsApi {
    @GET("/api/v1/medicalclinicproject/doctor/getAllMedics")
    suspend fun getMedics(): Resource<MedicsDTO>
}
