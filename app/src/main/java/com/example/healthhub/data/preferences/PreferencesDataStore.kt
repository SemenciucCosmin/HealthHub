package com.example.healthhub.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthhub.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = SHARED_PREFERENCES_NAME
    )

    val userFlow: Flow<User?> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val userId = preferences[USER_ID] ?: return@map null
            val userEmail = preferences[USER_EMAIL] ?: return@map null
            val userPassword = preferences[USER_PASSWORD] ?: return@map null

            User(
                id = userId,
                email = userEmail,
                password = userPassword
            )
        }

    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { it[USER_ID] = userId }
    }

    suspend fun saveUserEmail(userEmail: String) {
        context.dataStore.edit { preferences -> preferences[USER_EMAIL] = userEmail }
    }

    suspend fun saveUserPassword(userPassword: String) {
        context.dataStore.edit { preferences -> preferences[USER_PASSWORD] = userPassword }
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "health_hub_shared_preferences"
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_PASSWORD = stringPreferencesKey("user_password")
    }
}
