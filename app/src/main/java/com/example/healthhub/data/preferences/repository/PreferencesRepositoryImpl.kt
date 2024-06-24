package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore
) : PreferencesRepository {
    override suspend fun getSelectedId(): Flow<Int?> {
        return preferencesDataStore.userIdFlow
    }

    override suspend fun getUserInformation(): Flow<User?> {
        return preferencesDataStore.userFlow
    }

    override suspend fun getChildInformation(): Flow<User?> {
        return preferencesDataStore.childFlow
    }

    override suspend fun saveUserInformation(user: User) {
        preferencesDataStore.saveUser(user)
    }

    override suspend fun saveChildInformation(child: User) {
        preferencesDataStore.saveChild(child)
    }

    override suspend fun selectUser(id: Int) {
        preferencesDataStore.selectUser(id)
    }
}
