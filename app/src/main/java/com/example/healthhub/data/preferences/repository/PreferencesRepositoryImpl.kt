package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.model.User
import com.example.healthhub.data.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore
) : PreferencesRepository {
    override suspend fun getUserInformation(): Flow<User?> {
        return preferencesDataStore.userFlow
    }

    override suspend fun saveUserInformation(id: Int, email: String, password: String) {
        preferencesDataStore.saveUserId(id)
        preferencesDataStore.saveUserEmail(email)
        preferencesDataStore.saveUserPassword(password)
    }
}
