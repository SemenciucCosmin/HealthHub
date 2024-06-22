package com.example.healthhub.data.preferences

class PreferencesRepositoryImpl(
    private val preferencesDataStore: PreferencesDataStore
) : PreferencesRepository {
    override suspend fun saveUserInformation(id: Int, email: String, password: String) {
        preferencesDataStore.saveUserId(id)
        preferencesDataStore.saveUserEmail(email)
        preferencesDataStore.saveUserPassword(password)
    }
}
