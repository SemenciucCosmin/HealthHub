package com.example.healthhub.presentation.account.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.account.repository.AccountRepository
import com.example.healthhub.presentation.account.viewmodel.model.Account
import com.example.healthhub.presentation.account.viewmodel.model.AccountUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {
    var uiState by mutableStateOf<AccountUiState?>(null)
        private set

    init {
        viewModelScope.launch {
            val userFlow = accountRepository.getUserInformation().filterNotNull()
            val childFlow = accountRepository.getChildInformation()
            val selectedUserIdFlow = accountRepository.getSelectedUserId().filterNotNull()

            combine(userFlow, childFlow, selectedUserIdFlow) { user, child, selectedUserId ->
                Triple(user, child, selectedUserId)
            }.collectLatest { (user, child, selectedUserId) ->
                uiState = AccountUiState(
                    parentAccount = Account(
                        id = user.id,
                        name = "${user.firstname} ${user.lastname}",
                        isSelected = user.id == selectedUserId
                    ),
                    childAccount = child?.let {
                        Account(
                            id = child.id,
                            name = "${child.firstname} ${child.lastname}",
                            isSelected = child.id == selectedUserId
                        )
                    },
                    selectedUser = when (selectedUserId) {
                        child?.id -> child
                        else -> user
                    }
                )
            }
        }
    }

    fun selectAccount(id: Int) {
        viewModelScope.launch { accountRepository.selectUser(id) }
    }

    fun signOut() {
        viewModelScope.launch {
            accountRepository.clearUser()
        }
    }
}
