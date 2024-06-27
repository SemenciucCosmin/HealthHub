package com.example.healthhub.data.account.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.model.UsersInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = SHARED_PREFERENCES_NAME
    )

    val usersInfoFlow: Flow<UsersInfo?> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            val selectedUserId = preferences[SELECTED_USER_ID] ?: return@map null

            val parentId = preferences[PARENT_ID] ?: return@map null
            val parentEmail = preferences[PARENT_EMAIL] ?: return@map null
            val parentCnp = preferences[PARENT_CNP] ?: return@map null
            val parentSeries = preferences[PARENT_SERIES] ?: return@map null
            val parentLastName = preferences[PARENT_LASTNAME] ?: return@map null
            val parentFirstname = preferences[PARENT_FIRSTNAME] ?: return@map null
            val parentNationality = preferences[PARENT_NATIONALITY] ?: return@map null
            val parentDateOfBirth = preferences[PARENT_DATE] ?: return@map null
            val parentGender = preferences[PARENT_GENDER] ?: return@map null

            val childId = preferences[CHILD_ID]
            val childEmail = preferences[CHILD_EMAIL]
            val childCnp = preferences[CHILD_CNP]
            val childSeries = preferences[CHILD_SERIES]
            val childLastName = preferences[CHILD_LASTNAME]
            val childFirstname = preferences[CHILD_FIRSTNAME]
            val childNationality = preferences[CHILD_NATIONALITY]
            val childDateOfBirth = preferences[CHILD_DATE]
            val childGender = preferences[CHILD_GENDER]

            val parent = User(
                id = parentId,
                email = parentEmail,
                cnp = parentCnp,
                series = parentSeries,
                lastname = parentLastName,
                firstname = parentFirstname,
                nationality = parentNationality,
                dateOfBirth = parentDateOfBirth,
                gender = parentGender
            )

            val incompleteUsersInfo = UsersInfo(
                parent = parent,
                child = null,
                selectedUserId = selectedUserId
            )

            val child = User(
                id = childId ?: return@map incompleteUsersInfo,
                email = childEmail ?: return@map incompleteUsersInfo,
                cnp = childCnp ?: return@map incompleteUsersInfo,
                series = childSeries ?: return@map incompleteUsersInfo,
                lastname = childLastName ?: return@map incompleteUsersInfo,
                firstname = childFirstname ?: return@map incompleteUsersInfo,
                nationality = childNationality ?: return@map incompleteUsersInfo,
                dateOfBirth = childDateOfBirth ?: return@map incompleteUsersInfo,
                gender = childGender ?: return@map incompleteUsersInfo
            )

            UsersInfo(
                parent = parent,
                child = child,
                selectedUserId = selectedUserId
            )
        }

    suspend fun saveParent(user: User) {
        context.dataStore.edit { it[PARENT_ID] = user.id }
        context.dataStore.edit { preferences -> preferences[PARENT_EMAIL] = user.email }
        context.dataStore.edit { preferences -> preferences[PARENT_CNP] = user.cnp }
        context.dataStore.edit { preferences -> preferences[PARENT_SERIES] = user.series }
        context.dataStore.edit { preferences -> preferences[PARENT_LASTNAME] = user.lastname }
        context.dataStore.edit { preferences -> preferences[PARENT_FIRSTNAME] = user.firstname }
        context.dataStore.edit { preferences -> preferences[PARENT_NATIONALITY] = user.nationality }
        context.dataStore.edit { preferences -> preferences[PARENT_DATE] = user.dateOfBirth }
        context.dataStore.edit { preferences -> preferences[PARENT_GENDER] = user.gender }
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
        context.dataStore.edit { preferences -> preferences[CHILD_GENDER] = child.gender }
    }

    suspend fun selectUser(id: Int) {
        context.dataStore.edit { it[SELECTED_USER_ID] = id }
    }

    suspend fun clearUsersInfo() {
        context.dataStore.edit { it.clear() }
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "health_hub_shared_preferences"
        private val SELECTED_USER_ID = intPreferencesKey("selected_user_id")
        private val PARENT_ID = intPreferencesKey("parent_id")
        private val PARENT_EMAIL = stringPreferencesKey("parent_email")
        private val PARENT_CNP = stringPreferencesKey("parent_cnp")
        private val PARENT_SERIES = stringPreferencesKey("parent_series")
        private val PARENT_LASTNAME = stringPreferencesKey("parent_lastname")
        private val PARENT_FIRSTNAME = stringPreferencesKey("parent_firstname")
        private val PARENT_NATIONALITY = stringPreferencesKey("parent_nationality")
        private val PARENT_DATE = stringPreferencesKey("parent_date")
        private val PARENT_GENDER = stringPreferencesKey("parent_gender")
        private val CHILD_ID = intPreferencesKey("child_id")
        private val CHILD_EMAIL = stringPreferencesKey("child_email")
        private val CHILD_CNP = stringPreferencesKey("child_cnp")
        private val CHILD_SERIES = stringPreferencesKey("child_series")
        private val CHILD_LASTNAME = stringPreferencesKey("child_lastname")
        private val CHILD_FIRSTNAME = stringPreferencesKey("child_firstname")
        private val CHILD_NATIONALITY = stringPreferencesKey("child_nationality")
        private val CHILD_DATE = stringPreferencesKey("child_date")
        private val CHILD_GENDER = stringPreferencesKey("child_gender")
    }
}
