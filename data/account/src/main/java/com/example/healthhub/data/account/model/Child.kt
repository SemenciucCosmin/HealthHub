package com.example.healthhub.data.account.model

import com.example.healthhub.data.util.BLANK

data class Child(
    val id: Int,
    val surname: String,
    val firstname: String,
    val cnp: String,
    val dateOfBirth: String,
    val fatherSurname: String,
    val fatherFirstname: String,
    val motherSurname: String,
    val motherFirstName: String,
) {
    fun getNameInitials() = listOf(firstname, surname)
        .mapNotNull { it.firstOrNull() }
        .joinToString(String.BLANK)
}
