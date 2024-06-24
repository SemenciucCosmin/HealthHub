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
            val userDateOfBirth = preferences[USER_DATE] ?: return@map null
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

    val childFlow: Flow<User?> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val childId = preferences[CHILD_ID] ?: return@map null
            val childEmail = preferences[CHILD_EMAIL] ?: return@map null
            val childCnp = preferences[CHILD_CNP] ?: return@map null
            val childSeries = preferences[CHILD_SERIES] ?: return@map null
            val childLastName = preferences[CHILD_LASTNAME] ?: return@map null
            val childFirstname = preferences[CHILD_FIRSTNAME] ?: return@map null
            val childNationality = preferences[CHILD_NATIONALITY] ?: return@map null
            val childDateOfBirth = preferences[CHILD_DATE] ?: return@map null
            val childSex = preferences[CHILD_SEX] ?: return@map null

            User(
                id = childId,
                email = childEmail,
                cnp = childCnp,
                series = childSeries,
                lastname = childLastName,
                firstname = childFirstname,
                nationality = childNationality,
                dateOfBirth = childDateOfBirth,
                sex = childSex,
            )
        }

    val userIdFlow: Flow<Int?> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences -> preferences[SELECTED_USER_ID] }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { it[USER_ID] = user.id }
        context.dataStore.edit { preferences -> preferences[USER_EMAIL] = user.email }
        context.dataStore.edit { preferences -> preferences[USER_CNP] = user.cnp }
        context.dataStore.edit { preferences -> preferences[USER_SERIES] = user.series }
        context.dataStore.edit { preferences -> preferences[USER_LASTNAME] = user.lastname }
        context.dataStore.edit { preferences -> preferences[USER_FIRSTNAME] = user.firstname }
        context.dataStore.edit { preferences -> preferences[USER_NATIONALITY] = user.nationality }
        context.dataStore.edit { preferences -> preferences[USER_DATE] = user.dateOfBirth }
        context.dataStore.edit { preferences -> preferences[USER_SEX] = user.sex }
    }

    suspend fun saveChild(child: User) {
        context.dataStore.edit { it[CHILD_ID] = child.id }
        context.dataStore.edit { preferences -> preferences[CHILD_EMAIL] = child.email }
        context.dataStore.edit { preferences -> preferences[CHILD_CNP] = child.cnp }
        context.dataStore.edit { preferences -> preferences[CHILD_SERIES] = child.series }
        context.dataStore.edit { preferences -> preferences[CHILD_LASTNAME] = child.lastname }
        context.dataStore.edit { preferences -> preferences[CHILD_FIRSTNAME] = child.firstname }
        context.dataStore.edit { preferences -> preferences[CHILD_NATIONALITY] = child.nationality }
        context.dataStore.edit { preferences -> preferences[CHILD_DATE] = child.dateOfBirth }
        context.dataStore.edit { preferences -> preferences[CHILD_SEX] = child.sex }
    }

    suspend fun selectUser(id: Int) {
        context.dataStore.edit { it[SELECTED_USER_ID] = id }
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "health_hub_shared_preferences"
        private val SELECTED_USER_ID = intPreferencesKey("selected_user_id")
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_CNP = stringPreferencesKey("user_cnp")
        private val USER_SERIES = stringPreferencesKey("user_series")
        private val USER_LASTNAME = stringPreferencesKey("user_lastname")
        private val USER_FIRSTNAME = stringPreferencesKey("user_firstname")
        private val USER_NATIONALITY = stringPreferencesKey("user_nationality")
        private val USER_DATE = stringPreferencesKey("user_date")
        private val USER_SEX = stringPreferencesKey("user_sex")
        private val CHILD_ID = intPreferencesKey("child_id")
        private val CHILD_EMAIL = stringPreferencesKey("child_email")
        private val CHILD_CNP = stringPreferencesKey("child_cnp")
        private val CHILD_SERIES = stringPreferencesKey("child_series")
        private val CHILD_LASTNAME = stringPreferencesKey("child_lastname")
        private val CHILD_FIRSTNAME = stringPreferencesKey("child_firstname")
        private val CHILD_NATIONALITY = stringPreferencesKey("child_nationality")
        private val CHILD_DATE = stringPreferencesKey("child_date")
        private val CHILD_SEX = stringPreferencesKey("child_sex")
    }
}
