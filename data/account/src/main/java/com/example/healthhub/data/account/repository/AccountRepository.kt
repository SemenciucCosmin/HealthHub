package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.network.resource.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface AccountRepository {
    suspend fun getUsersInfo(): Flow<UsersInfo>
    suspend fun selectUser(id: Int)
    suspend fun clearUsersInfo()
    suspend fun setupUsersInfo(parentUserId: Int)
    suspend fun uploadBirthCertificate(parentId: Int, imageFile: File): Resource<Boolean>
    suspend fun changeEmail(newEmail: String, parentId: Int): Resource<Boolean>
    suspend fun changePassword(newPassword: String, parentId: Int): Resource<Boolean>
}