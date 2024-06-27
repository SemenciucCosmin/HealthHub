package com.example.healthhub.network.api.model

data class AuthenticationRequestBody(
    private val email: String,
    private val password: String
) : RequestBody {
    override fun build() = mapOf(
        "email" to email,
        "password" to password
    )
}
