package com.example.healthhub.network.api.model

data class AccountValidationRequestBody(
    private val email: String
) : RequestBody {
    override fun build() = mapOf(
        "email" to email
    )
}
