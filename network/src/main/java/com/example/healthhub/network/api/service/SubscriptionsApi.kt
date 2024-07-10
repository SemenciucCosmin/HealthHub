package com.example.healthhub.network.api.service

import com.example.healthhub.network.api.model.AllSubscriptionsDTO
import com.example.healthhub.network.api.model.SubscriptionDetailsDTO
import com.example.healthhub.network.api.model.UserSubscriptionsDTO
import com.example.healthhub.network.resource.Resource
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SubscriptionsApi {
    @GET("/api/v1/medicalclinicproject/subs/getSubscriptionsByUserId/{userId}")
    suspend fun getUserSubscriptions(@Path("userId") userId: Int): Resource<UserSubscriptionsDTO>

    @GET("/api/v1/medicalclinicproject/admin/getAllSubscription")
    suspend fun getAllSubscriptions(): Resource<AllSubscriptionsDTO>

    @POST("/api/v1/medicalclinicproject/subs/addSubscriptionToUser")
    suspend fun addSubscription(@Body requestBody: RequestBody): Resource<Unit>

    @POST("/api/v1/medicalclinicproject/appointments/getSpecializationsAndServicesInfo")
    suspend fun getUserSubscriptionDetails(
        @Body params: Map<String, Int>
    ): Resource<SubscriptionDetailsDTO>
}
