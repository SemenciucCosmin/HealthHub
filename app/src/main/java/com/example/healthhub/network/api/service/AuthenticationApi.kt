package com.example.healthhub.network.api.service

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApi {
    @FormUrlEncoded
    @POST("/auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Unit

    @FormUrlEncoded
    @POST("/auth/register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Unit

    @FormUrlEncoded
    @POST("/img/registerUserBasedOnIdCard")
    fun uploadId(
        @Field("email") email: String,
        @Field("file") file: String,
    ): Unit

    @FormUrlEncoded
    @POST("/getAccountValidationStatus")
    fun getAccountValidationStatus(@Field("email") email: String): Unit
}