package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.account.preferences.PreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class AccountMockedRepositoryImpl(
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
        val parent = User(
            id = 1,
            email = "parent.email@healthhub.com",
            cnp = "PRNT_123456789",
            series = "PRNT_SRS",
            lastname = "User",
            firstname = "Parent",
            nationality = "PRNT_NTNLT",
            dateOfBirth = "pp-pp-pppp",
            gender = "PRNT_GNDR"
        )

        val child = User(
            id = 2,
            email = "child.email@healthhub.com",
            cnp = "CHLD_123456789",
            series = "CHLD_SRS",
            lastname = "User",
            firstname = "Child",
            nationality = "CHLD_NTNLT",
            dateOfBirth = "cc-cc-cccc",
            gender = "CHLD_GNDR"
        )

        preferencesDataStore.saveParent(parent)
        preferencesDataStore.saveChild(child)
        preferencesDataStore.selectUser(parent.id)
    }
}