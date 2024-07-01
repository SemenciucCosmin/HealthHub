package com.example.healthhub.data.appointments.model

data class Specialization(
    val id: Int,
    val name: String,
    val description: String,
    val services: List<Service>,
)
