package com.example.healthhub.data.authentication.repository

import android.net.Uri
import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AuthenticationRepositoryImpl(
    private val authenticationApi: AuthenticationApi
) : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Resource<LoginStatus> {
        val resource = authenticationApi.login(email, password)
        val loginFlowDto = resource.payload

        return when {
            loginFlowDto?.userId != null -> {
                Resource(LoginStatus.Success(loginFlowDto.userId), resource.status)
            }

            loginFlowDto?.developerReason?.contains("email") == true -> {
                Resource(LoginStatus.EmailError, resource.status)
            }

            loginFlowDto?.developerReason?.contains("password") == true -> {
                Resource(LoginStatus.PasswordError, resource.status)
            }

            else -> Resource(null, resource.status)
        }
    }

    override suspend fun register(email: String, password: String): Resource<Boolean> {
        val resource = authenticationApi.register(email, password)
        return Resource(null, resource.status)
    }

    override suspend fun getAccountValidationStatus(email: String): Resource<Boolean> {
        val resource = authenticationApi.getAccountValidationStatus(email)
        return Resource(resource.payload?.validation, resource.status)
    }

    override suspend fun uploadID(email: String, imageUri: Uri): Resource<IdValidation> {
        val imageFile = imageUri.path?.let { File(it) } ?: run {
            return Resource(null, Status.ResourceAccessError)
        }

        val requestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
        val multiPart = MultipartBody.Part.createFormData("image", "image", requestBody)
        val body = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addPart(multiPart)
            addFormDataPart("email", email)
        }.build()

        val resource = authenticationApi.uploadId(body)
        return Resource(
            payload = IdValidation(
                updated = resource.payload?.updated ?: false,
                integrity = resource.payload?.integrity ?: false,
            ),
            status = resource.status
        )
    }
}