package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.account.model.User
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun getSelectedId(): Flow<Int?>
    suspend fun getUserInformation(): Flow<User?>
    suspend fun getChildInformation(): Flow<User?>
    suspend fun saveUserInformation(user: User)
    suspend fun saveChildInformation(child: User)
    suspend fun selectUser(id: Int)
}
