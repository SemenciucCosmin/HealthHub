package com.example.healthhub.domain.account

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.repository.AccountRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transformLatest

class GetUserUseCase(private val accountRepository: AccountRepository) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<User?> {
        val selectedUserIdFlow = accountRepository.getSelectedUserId()
        val userFlow = accountRepository.getUserInformation()
        val childFlow = accountRepository.getChildInformation()

        return combine(selectedUserIdFlow, userFlow, childFlow) { selectedUserId, user, child ->
            Triple(selectedUserId, user, child)
        }.transformLatest { (selectedUserId, user, child) ->
            when (selectedUserId) {
                user?.id -> userFlow.collectLatest(::emit)
                child?.id -> childFlow.collectLatest(::emit)
            }
        }
    }
}