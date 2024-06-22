package com.example.healthhub.data.authentication.repository

import android.net.Uri
import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.resource.Resource

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Resource<LoginStatus>
    suspend fun register(email: String, password: String): Resource<Boolean>
    suspend fun getAccountValidationStatus(email: String): Resource<Boolean>
    suspend fun uploadID(email: String, imageUri: Uri): Resource<IdValidation>
}