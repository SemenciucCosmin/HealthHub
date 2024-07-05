package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.BirthCertificateValidationDTO
import com.example.healthhub.network.api.model.UserChildInformationDTO
import com.example.healthhub.network.api.model.UserInformationDTO
import com.example.healthhub.network.resource.Resource
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {
    @GET("/api/v1/medicalclinicproject/user/getDataById/{parentId}")
    suspend fun getUserInformation(@Path("parentId") parentId: Int): Resource<UserInformationDTO>

    @GET("/api/v1/medicalclinicproject/user/getUserChild/{parentId}")
    suspend fun getUserChildInformation(@Path("parentId") parentId: Int): Resource<UserChildInformationDTO>

    @POST("/api/v1/medicalclinicproject/img/validateBirthCertificate/{parentId}")
    suspend fun uploadBirthCertificate(
        @Path("parentId") parentId: Int,
        @Body requestBody: RequestBody
    ): Resource<BirthCertificateValidationDTO>
}
