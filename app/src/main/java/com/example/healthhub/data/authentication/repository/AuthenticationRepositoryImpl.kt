package com.example.healthhub.data.authentication.repository

import com.example.healthhub.data.authentication.model.LoginStatus
import com.example.healthhub.network.api.service.AuthenticationApi
import com.example.healthhub.network.resource.Resource

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

            else -> {
                Resource.Success(LoginStatus.PasswordError)
            }
        }
    }
}