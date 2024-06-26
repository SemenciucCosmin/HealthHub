package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.LocationsDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET

interface LocationsApi {
    @GET("/api/v1/medicalclinicproject/user/getAllLocations")
    suspend fun getLocations(): Resource<LocationsDTO>
}
