package com.example.healthhub.data.authentication.repository

import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.resource.Resource

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Resource<LoginStatus>
    suspend fun register(email: String, password: String): Resource<Boolean>
    suspend fun getAccountValidationStatus(email: String): Resource<Boolean>
}