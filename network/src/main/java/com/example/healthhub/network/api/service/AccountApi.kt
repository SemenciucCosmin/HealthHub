package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.UserChildInformationDTO
import com.example.healthhub.network.api.model.UserInformationDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("/api/v1/medicalclinicproject/user/getDataById/{parentId}")
    suspend fun getUserInformation(@Path("parentId") parentId: Int): Resource<UserInformationDTO>

    @GET("/api/v1/medicalclinicproject/user/getUserChild/{parentId}")
    suspend fun getUserChildInformation(@Path("parentId") parentId: Int): Resource<UserChildInformationDTO>
}
