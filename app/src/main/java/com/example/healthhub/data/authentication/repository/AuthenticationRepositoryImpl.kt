package com.example.healthhub.data.authentication.repository

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
        val resource = authenticationApi.login(
            mapOf(
                "email" to email,
                "password" to password,
            )
        )

        val loginFlowDto = resource.payload?.innerLoginFlowDTO

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
        val resource = authenticationApi.register(
            mapOf(
                "email" to email,
                "password" to password,
            )
        )

        return Resource(resource.status == Status.Success, resource.status)
    }

    override suspend fun getAccountValidationStatus(email: String): Resource<Boolean> {
        val resource = authenticationApi.getAccountValidationStatus(mapOf("email" to email))

        return Resource(resource.payload?.innerAccountRegistrationDTO?.validation, resource.status)
    }

    override suspend fun uploadID(email: String, imageFile: File): Resource<IdValidation> {
        val requestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
        val multiPart = MultipartBody.Part.createFormData("picture", "image", requestBody)
        val body = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addPart(multiPart)
            addFormDataPart("email", email)
        }.build()

        val resource = authenticationApi.uploadId(body)
        return Resource(
            payload = IdValidation(
                updated = resource.payload?.innerIdValidationDTO?.updated ?: false,
                integrity = resource.payload?.innerIdValidationDTO?.integrity ?: false,
            ),
            status = resource.status
        )
    }
}