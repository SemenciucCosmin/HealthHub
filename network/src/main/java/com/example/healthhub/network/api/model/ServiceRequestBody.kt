package com.example.healthhub.network.api.model

data class ServiceRequestBody(
    private val id: Int,
    private val name: String,
    private val description: String,
    private val price: Float,
    private val duration: Int,
) : RequestBody {
    override fun build() = mapOf(
        "id" to id,
        "name" to name,
        "description" to description,
        "price" to price,
        "duration" to duration,
    )
}
