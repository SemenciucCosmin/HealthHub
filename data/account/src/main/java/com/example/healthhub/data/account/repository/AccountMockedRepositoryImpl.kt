package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.account.preferences.PreferencesDataStore
import com.example.healthhub.data.authentication.model.IdValidation
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import java.io.File

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
        val parent = Parent(
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

        val child = Child(
            id = 2,
            surname = "User",
            firstname = "Child",
            cnp = "CHLD_123456789",
            dateOfBirth = "cc-cc-cccc",
            fatherSurname = "FATHER_SURNAME",
            fatherFirstname = "FATHER_FIRSTNAME",
            motherSurname = "MOTHER_SURNAME",
            motherFirstName = "MOTHER_FIRSTNAME"
        )

        preferencesDataStore.saveParent(parent)
        preferencesDataStore.saveChild(child)
        preferencesDataStore.selectUser(parent.id)
    }

    override suspend fun uploadBirthCertificate(
        parentId: Int,
        imageFile: File
    ): Resource<Boolean> {
        return Resource(
            status = Status.Success,
            payload = true
        )
    }
}