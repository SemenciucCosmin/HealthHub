package com.example.healthhub.data.preferences.repository

interface PreferencesRepository {
    suspend fun saveUserInformation(id: Int, email: String, password: String)
}
