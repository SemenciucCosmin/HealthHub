package com.example.healthhub.data.appointments.model

data class Service(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val discountedPrice: Float,
    val duration: Int,
)
