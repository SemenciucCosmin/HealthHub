package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.model.User
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun getUserInformation(): Flow<User?>
    suspend fun saveUserInformation(id: Int, email: String, password: String)
}
