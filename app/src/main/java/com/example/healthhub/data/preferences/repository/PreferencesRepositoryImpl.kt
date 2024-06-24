package com.example.healthhub.data.preferences.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore
) : PreferencesRepository {
    override suspend fun getUserInformation(): Flow<User?> {
        return preferencesDataStore.userFlow
    }

    override suspend fun saveUserInformation(user: User) {
        preferencesDataStore.saveUserId(user.id)
        preferencesDataStore.saveUserEmail(user.email)
        preferencesDataStore.saveUserCnp(user.cnp)
        preferencesDataStore.saveUserSeries(user.series)
        preferencesDataStore.saveUserLastname(user.lastname)
        preferencesDataStore.saveUserFirstname(user.firstname)
        preferencesDataStore.saveUserNationality(user.nationality)
        preferencesDataStore.saveUserDateOfBirth(user.dateOfBirth)
        preferencesDataStore.saveUserSex(user.sex)
    }
}
