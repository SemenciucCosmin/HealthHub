package com.example.healthhub.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthhub.data.account.model.User
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
            val userCnp = preferences[USER_CNP] ?: return@map null
            val userSeries = preferences[USER_SERIES] ?: return@map null
            val userLastName = preferences[USER_LASTNAME] ?: return@map null
            val userFirstname = preferences[USER_FIRSTNAME] ?: return@map null
            val userNationality = preferences[USER_NATIONALITY] ?: return@map null
            val userDateOfBirth = preferences[USER_DATE_OF_BIRTH] ?: return@map null
            val userSex = preferences[USER_SEX] ?: return@map null

            User(
                id = userId,
                email = userEmail,
                cnp = userCnp,
                series = userSeries,
                lastname = userLastName,
                firstname = userFirstname,
                nationality = userNationality,
                dateOfBirth = userDateOfBirth,
                sex = userSex,
            )
        }

    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { it[USER_ID] = userId }
    }

    suspend fun saveUserEmail(userEmail: String) {
        context.dataStore.edit { preferences -> preferences[USER_EMAIL] = userEmail }
    }

    suspend fun saveUserCnp(userCnp: String) {
        context.dataStore.edit { preferences -> preferences[USER_CNP] = userCnp }
    }

    suspend fun saveUserSeries(userSeries: String) {
        context.dataStore.edit { preferences -> preferences[USER_SERIES] = userSeries }
    }

    suspend fun saveUserLastname(userLastname: String) {
        context.dataStore.edit { preferences -> preferences[USER_LASTNAME] = userLastname }
    }

    suspend fun saveUserFirstname(userFirstname: String) {
        context.dataStore.edit { preferences -> preferences[USER_FIRSTNAME] = userFirstname }
    }

    suspend fun saveUserNationality(userNationality: String) {
        context.dataStore.edit { preferences -> preferences[USER_NATIONALITY] = userNationality }
    }

    suspend fun saveUserDateOfBirth(userDateOfBirth: String) {
        context.dataStore.edit { preferences -> preferences[USER_DATE_OF_BIRTH] = userDateOfBirth }
    }

    suspend fun saveUserSex(userSex: String) {
        context.dataStore.edit { preferences -> preferences[USER_SEX] = userSex }
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "health_hub_shared_preferences"
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_CNP = stringPreferencesKey("user_cnp")
        private val USER_SERIES = stringPreferencesKey("user_series")
        private val USER_LASTNAME = stringPreferencesKey("user_lastname")
        private val USER_FIRSTNAME = stringPreferencesKey("user_firstname")
        private val USER_NATIONALITY = stringPreferencesKey("user_nationality")
        private val USER_DATE_OF_BIRTH = stringPreferencesKey("user_date_of_birth")
        private val USER_SEX = stringPreferencesKey("user_sex")
    }
}
