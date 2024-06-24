package com.example.healthhub.domain.account

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.repository.AccountRepository
import com.example.healthhub.network.resource.Resource
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): Flow<User?> {
        return accountRepository.getUserInformation()
    }
}