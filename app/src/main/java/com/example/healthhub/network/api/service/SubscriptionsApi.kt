package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.SubscriptionsDTO
import com.example.healthhub.network.api.model.UserInformationDTO
import com.example.healthhub.network.resource.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface SubscriptionsApi {
    @GET("/api/v1/medicalclinicproject/subs/getSubscriptionsByUserId/{userId}")
    suspend fun getUserSubscriptions(@Path("userId") userId: Int): Resource<SubscriptionsDTO>
}
