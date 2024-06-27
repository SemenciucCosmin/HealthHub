package com.example.healthhub.domain.account

import com.example.healthhub.data.account.repository.AccountRepository

class SetUserInformationUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(parentUserId: Int) {
        accountRepository.setupUsersInfo(parentUserId)
    }
}