package com.example.healthhub.domain.account

import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.account.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetUsersInfoUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): Flow<UsersInfo> {
        return accountRepository.getUsersInfo()
    }
}