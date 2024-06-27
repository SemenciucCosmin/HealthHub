package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.account.preferences.PreferencesDataStore
import com.example.healthhub.network.api.service.AccountApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val preferencesDataStore: PreferencesDataStore,
) : AccountRepository {
    override suspend fun getUsersInfo(): Flow<UsersInfo> {
        return preferencesDataStore.usersInfoFlow.filterNotNull()
    }

    override suspend fun selectUser(id: Int) {
        preferencesDataStore.selectUser(id)
    }

    override suspend fun clearUsersInfo() {
        preferencesDataStore.clearUsersInfo()
    }

    override suspend fun setupUsersInfo(parentUserId: Int) {
        val parentResource = accountApi.getUserInformation(parentUserId)
        val childResource = accountApi.getUserInformation(parentUserId)
        parentResource.payload?.innerUserInformationDTO?.let { parentInformationDto ->
            val parent = User(
                id = parentInformationDto.id ?: return,
                email = parentInformationDto.email ?: return,
                cnp = parentInformationDto.cnp ?: return,
                series = parentInformationDto.series ?: return,
                lastname = parentInformationDto.lastname ?: return,
                firstname = parentInformationDto.firstname ?: return,
                nationality = parentInformationDto.nationality ?: return,
                dateOfBirth = parentInformationDto.dateOfBirth ?: return,
                gender = parentInformationDto.gender ?: return
            )

            preferencesDataStore.saveParent(parent)
            preferencesDataStore.selectUser(parent.id)

            childResource.payload?.innerUserInformationDTO?.let { childInformationDto ->
                val child = User(
                    id = childInformationDto.id ?: return,
                    email = childInformationDto.email ?: return,
                    cnp = childInformationDto.cnp ?: return,
                    series = childInformationDto.series ?: return,
                    lastname = childInformationDto.lastname ?: return,
                    firstname = childInformationDto.firstname ?: return,
                    nationality = childInformationDto.nationality ?: return,
                    dateOfBirth = childInformationDto.dateOfBirth ?: return,
                    gender = childInformationDto.gender ?: return
                )

                preferencesDataStore.saveChild(child)
            }
        }
    }
}