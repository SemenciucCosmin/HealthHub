package com.example.healthhub.data.account.model

import com.example.healthhub.data.util.BLANK

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
) {
    fun getNameInitials() = listOf(firstname, lastname)
        .mapNotNull { it.firstOrNull() }
        .joinToString(String.BLANK)
}
