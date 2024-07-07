package com.example.healthhub.data.home.model

data class Subscription(
    val id: Int,
    val name: String,
    val active: Boolean,
    val pricePerMonth: Double,
    val period: Int,
    val specializationId: Int,
) {
    companion object {
        const val INVALID_ID = -1
    }
}
