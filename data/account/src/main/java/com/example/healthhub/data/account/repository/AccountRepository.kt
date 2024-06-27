package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.UsersInfo
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getUsersInfo(): Flow<UsersInfo>
    suspend fun selectUser(id: Int)
    suspend fun clearUsersInfo()
    suspend fun setupUsersInfo(parentUserId: Int)
}