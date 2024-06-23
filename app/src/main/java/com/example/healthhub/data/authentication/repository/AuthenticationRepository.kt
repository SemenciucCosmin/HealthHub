package com.example.healthhub.data.authentication.repository

import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.resource.Resource
import java.io.File

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Resource<LoginStatus>
    suspend fun register(email: String, password: String): Resource<Boolean>
    suspend fun getAccountValidationStatus(email: String): Resource<Boolean>
    suspend fun uploadID(email: String, imageFile: File): Resource<IdValidation>
}