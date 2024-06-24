package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.account.model.User
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun getUserInformation(): Flow<User?>
    suspend fun saveUserInformation(user: User)
}
