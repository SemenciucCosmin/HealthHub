package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.CountiesDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET

interface CountiesApi {
    @GET("/api/v1/medicalclinicproject/appointments/getAllCounties")
    suspend fun getCounties(): Resource<CountiesDTO>
}
