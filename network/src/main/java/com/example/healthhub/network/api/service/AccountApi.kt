package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.UserInformationDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("/api/v1/medicalclinicproject/user/getDataById/{parentId}")
    suspend fun getUserInformation(@Path("parentId") parentId: Int): Resource<UserInformationDTO>
}
