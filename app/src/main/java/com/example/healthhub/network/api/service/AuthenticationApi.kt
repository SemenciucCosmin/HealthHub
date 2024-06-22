package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.AccountRegistrationDTO
import com.example.healthhub.network.api.model.IdValidationDTO
import com.example.healthhub.network.api.model.LoginFlowDTO
import com.example.healthhub.network.api.model.RegisterFlowDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApi {
    @FormUrlEncoded
    @POST("/api/v1/medicalclinicproject/auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Resource<LoginFlowDTO>

    @FormUrlEncoded
    @POST("/api/v1/medicalclinicproject/auth/register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Resource<RegisterFlowDTO>

    @FormUrlEncoded
    @POST("/api/v1/medicalclinicproject/img/registerUserBasedOnIdCard")
    suspend fun uploadId(
        @Field("email") email: String,
        @Field("file") file: String,
    ): Resource<IdValidationDTO>

    @FormUrlEncoded
    @POST("/api/v1/medicalclinicproject/getAccountValidationStatus")
    suspend fun getAccountValidationStatus(
        @Field("email") email: String
    ): Resource<AccountRegistrationDTO>
}