package com.example.healthhub.data.preferences

interface PreferencesRepository {
    suspend fun saveUserInformation(id: Int, email: String, password: String)
}
