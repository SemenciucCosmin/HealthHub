package com.example.healthhub.data.account.model

data class User(
    val id: Int,
    val email: String,
    val cnp: String,
    val series: String,
    val lastname: String,
    val firstname: String,
    val nationality: String,
    val dateOfBirth: String,
    val sex: String,
)
