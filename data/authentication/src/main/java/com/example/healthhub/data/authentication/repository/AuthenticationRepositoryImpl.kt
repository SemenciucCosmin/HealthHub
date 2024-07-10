package com.example.healthhub.data.authentication.repository

import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.data.authentication.model.RegisterStatus
import com.example.healthhub.network.api.model.AccountValidationRequestBody
import com.example.healthhub.network.api.model.AuthenticationRequestBody
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.resource.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AuthenticationRepositoryImpl(
    private val authenticationApi: AuthenticationApi,
) : AuthenticationRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Resource<LoginStatus> {
        val accountStatusResource = authenticationApi.getAccountValidationStatus(
            AccountValidationRequestBody(email).build()
        )

        val loginResource = authenticationApi.login(
            AuthenticationRequestBody(email, password).build()
        )

        val loginFlowDto = loginResource.payload?.innerLoginFlowDTO
        val userId = loginFlowDto?.userId

        return when {
            userId != null -> {
                if (accountStatusResource.payload?.innerAccountRegistrationDTO?.validation == true) {
                    Resource(
                        LoginStatus.Success(userId),
                        loginResource.status
                    )
                } else {
                    Resource(
                        LoginStatus.EmailError,
                        loginResource.status
                    )
                }
            }

            loginFlowDto?.developerReason?.contains("email") == true -> {
                Resource(
                    LoginStatus.EmailError,
                    loginResource.status
                )
            }

            loginFlowDto?.developerReason?.contains("password") == true -> {
                Resource(
                    LoginStatus.PasswordError,
                    loginResource.status
                )
            }

            else -> Resource(null, loginResource.status)
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Resource<RegisterStatus> {
        val resource = authenticationApi.register(
            AuthenticationRequestBody(email, password).build()
        )

        val userId = resource.payload?.innerRegisterFlowDTO?.model?.userId

        return Resource(
            userId?.let { RegisterStatus(it) },
            resource.status
        )
    }

    override suspend fun getAccountValidationStatus(email: String): Resource<Boolean> {
        val resource = authenticationApi.getAccountValidationStatus(mapOf("email" to email))

        return Resource(
            resource.payload?.innerAccountRegistrationDTO?.validation,
            resource.status
        )
    }

    override suspend fun uploadID(
        email: String,
        imageFile: File
    ): Resource<IdValidation> {
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