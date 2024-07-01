package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.SpecializationsDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET

interface SpecializationsApi {
    @GET("/api/v1/medicalclinicproject/appointments/getAllSpecializations")
    suspend fun getSpecializations(): Resource<SpecializationsDTO>
}
