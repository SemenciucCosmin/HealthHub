package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
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
        val childResource = accountApi.getUserChildInformation(parentUserId)
        parentResource.payload?.innerUserInformationDTO?.let { parentInformationDto ->
            val parent = Parent(
                id = parentUserId,
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

            childResource.payload?.innerUserChildInformationDTO?.entities?.firstOrNull()?.let {
                val child = Child(
                    id = it.id ?: return,
                    surname = it.surname ?: return,
                    firstname = it.firstname ?: return,
                    cnp = it.cnp ?: return,
                    dateOfBirth = it.dateOfBirth ?: return,
                    fatherSurname = it.fatherSurname ?: return,
                    fatherFirstname = it.fatherFirstname ?: return,
                    motherSurname = it.motherSurname ?: return,
                    motherFirstName = it.motherFirstName ?: return
                )

                preferencesDataStore.saveChild(child)
            }
        }
    }
}