package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.network.api.service.AccountApi
import com.example.healthhub.data.account.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val preferencesDataStore: PreferencesDataStore,
) : AccountRepository {
    override suspend fun getSelectedUserId(): Flow<Int?> {
        return preferencesDataStore.userIdFlow
    }

    override suspend fun getUserInformation(): Flow<User?> {
        return preferencesDataStore.userFlow
    }

    override suspend fun getChildInformation(): Flow<User?> {
        return preferencesDataStore.childFlow
    }

    override suspend fun setUserInformation(userId: Int) {
        val resource = accountApi.getUserInformation(userId)
        resource.payload?.innerUserInformationDTO?.let { userInformationDto ->
            val user = User(
                id = userId,
                email = userInformationDto.email,
                cnp = userInformationDto.cnp,
                series = userInformationDto.series,
                lastname = userInformationDto.lastname,
                firstname = userInformationDto.firstname,
                nationality = userInformationDto.nationality,
                dateOfBirth = userInformationDto.dateOfBirth,
                sex = userInformationDto.sex,
            )

            preferencesDataStore.saveUser(user)
            preferencesDataStore.selectUser(user.id)
        }
    }

    override suspend fun selectUser(id: Int) {
        preferencesDataStore.selectUser(id)
    }

    override suspend fun clearUser() {
        preferencesDataStore.clearUser()
    }
}