package com.example.healthhub.data.authentication.repository

import android.net.Uri
import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.resource.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AuthenticationRepositoryImpl(
    private val authenticationApi: AuthenticationApi
) : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Resource<LoginStatus> {
        val resource = authenticationApi.login(email, password)
        resource.errorOrNull()?.let { return it.getErrorType() }
        val loginFlowDto = resource.getOrNull() ?: return Resource.Error.NotFound()

        return when {
            loginFlowDto.userId != null -> {
                Resource.Success(LoginStatus.Success(loginFlowDto.userId))
            }

            loginFlowDto.developerReason?.contains("email") == true -> {
                Resource.Success(LoginStatus.EmailError)
            }

            else -> Resource.Success(LoginStatus.PasswordError)
        }
    }

    override suspend fun register(email: String, password: String): Resource<Boolean> {
        val resource = authenticationApi.register(email, password)
        resource.errorOrNull()?.let { return it.getErrorType() }
        return Resource.Success(resource.isSuccessful)
    }

    override suspend fun getAccountValidationStatus(email: String): Resource<Boolean> {
        val resource = authenticationApi.getAccountValidationStatus(email)
        resource.errorOrNull()?.let { return it.getErrorType() }
        val accountRegistrationDto = resource.getOrNull() ?: return Resource.Error.NotFound()
        return Resource.Success(accountRegistrationDto.validation == true)
    }

    override suspend fun uploadID(email: String, imageUri: Uri): Resource<IdValidation> {
        val imageFile = imageUri.path?.let { File(it) } ?: return Resource.Error.Access()
        val requestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
        val multiPart = MultipartBody.Part.createFormData("image", "image", requestBody)
        val body = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addPart(multiPart)
            addFormDataPart("email", email)
        }.build()

        val resource = authenticationApi.uploadId(body)
        resource.errorOrNull()?.let { return it.getErrorType() }
        val idValidationDto = resource.getOrNull() ?: return Resource.Error.NotFound()
        return Resource.Success(
            IdValidation(
                updated = idValidationDto.updated ?: false,
                integrity = idValidationDto.integrity ?: false,
            )
        )
    }
}