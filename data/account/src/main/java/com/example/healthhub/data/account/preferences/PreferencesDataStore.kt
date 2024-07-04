package com.example.healthhub.data.account.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
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
            val childSurname = preferences[CHILD_SURNAME]
            val childFirstname = preferences[CHILD_FIRSTNAME]
            val childCnp = preferences[CHILD_CNP]
            val childDateOfBirth = preferences[CHILD_DATE]
            val childFatherSurname = preferences[CHILD_F_SURNAME]
            val childFatherFirstname = preferences[CHILD_F_FIRSTNAME]
            val childMotherSurname = preferences[CHILD_M_SURNAME]
            val childMotherFirstname = preferences[CHILD_M_FIRSTNAME]

            val parent = Parent(
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

            val child = Child(
                id = childId ?: return@map incompleteUsersInfo,
                surname = childSurname ?: return@map incompleteUsersInfo,
                firstname = childFirstname ?: return@map incompleteUsersInfo,
                cnp = childCnp ?: return@map incompleteUsersInfo,
                dateOfBirth = childDateOfBirth ?: return@map incompleteUsersInfo,
                fatherSurname = childFatherSurname ?: return@map incompleteUsersInfo,
                fatherFirstname = childFatherFirstname ?: return@map incompleteUsersInfo,
                motherSurname = childMotherSurname ?: return@map incompleteUsersInfo,
                motherFirstName = childMotherFirstname ?: return@map incompleteUsersInfo,
            )

            UsersInfo(
                parent = parent,
                child = child,
                selectedUserId = selectedUserId
            )
        }

    suspend fun saveParent(parent: Parent) {
        context.dataStore.edit { it[PARENT_ID] = parent.id }
        context.dataStore.edit { preferences -> preferences[PARENT_EMAIL] = parent.email }
        context.dataStore.edit { preferences -> preferences[PARENT_CNP] = parent.cnp }
        context.dataStore.edit { preferences -> preferences[PARENT_SERIES] = parent.series }
        context.dataStore.edit { preferences -> preferences[PARENT_LASTNAME] = parent.lastname }
        context.dataStore.edit { preferences -> preferences[PARENT_FIRSTNAME] = parent.firstname }
        context.dataStore.edit { preferences ->
            preferences[PARENT_NATIONALITY] = parent.nationality
        }
        context.dataStore.edit { preferences -> preferences[PARENT_DATE] = parent.dateOfBirth }
        context.dataStore.edit { preferences -> preferences[PARENT_GENDER] = parent.gender }
    }

    suspend fun saveChild(child: Child) {
        context.dataStore.edit { preferences ->
            preferences[CHILD_ID] = child.id
            preferences[CHILD_SURNAME] = child.surname
            preferences[CHILD_FIRSTNAME] = child.firstname
            preferences[CHILD_CNP] = child.cnp
            preferences[CHILD_DATE] = child.dateOfBirth
            preferences[CHILD_F_SURNAME] = child.fatherSurname
            preferences[CHILD_F_FIRSTNAME] = child.fatherFirstname
            preferences[CHILD_M_SURNAME] = child.motherSurname
            preferences[CHILD_M_FIRSTNAME] = child.motherFirstName
        }
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
        private val CHILD_SURNAME = stringPreferencesKey("child_surname")
        private val CHILD_FIRSTNAME = stringPreferencesKey("child_firstname")
        private val CHILD_CNP = stringPreferencesKey("child_cnp")
        private val CHILD_DATE = stringPreferencesKey("child_date")
        private val CHILD_F_SURNAME = stringPreferencesKey("child_f_surname")
        private val CHILD_F_FIRSTNAME = stringPreferencesKey("child_f_firstname")
        private val CHILD_M_SURNAME = stringPreferencesKey("child_m_surname")
        private val CHILD_M_FIRSTNAME = stringPreferencesKey("child_m_firstname")
    }
}
