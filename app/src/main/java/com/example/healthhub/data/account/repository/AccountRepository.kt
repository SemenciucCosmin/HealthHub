package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.User
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getSelectedUserId(): Flow<Int?>
    suspend fun getUserInformation(): Flow<User?>
    suspend fun getChildInformation(): Flow<User?>
    suspend fun setUserInformation(userId: Int)
    suspend fun selectUser(id: Int)
}