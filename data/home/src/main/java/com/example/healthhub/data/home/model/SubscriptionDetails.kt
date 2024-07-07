package com.example.healthhub.data.home.model

import com.example.healthhub.data.appointments.model.Service

data class SubscriptionDetails(
    val id: Int,
    val name: String,
    val active: Boolean,
    val pricePerMonth: Double,
    val period: Int,
    val specializationId: Int,
    val specializationName: String,
    val specializationDescription: String,
    val services: List<Service>,
)
