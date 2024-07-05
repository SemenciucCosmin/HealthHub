package com.example.healthhub.feature.account.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.account.repository.AccountRepository
import com.example.healthhub.domain.account.SetUserInformationUseCase
import com.example.healthhub.feature.account.viewmodel.model.Account
import com.example.healthhub.feature.account.viewmodel.model.AccountUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File

class AccountViewModel(
    private val accountRepository: AccountRepository,
    private val setUserInformationUseCase: SetUserInformationUseCase
) : ViewModel() {
    var uiState by mutableStateOf<AccountUiState?>(null)
        private set

    init {
        viewModelScope.launch {
            accountRepository.getUsersInfo().collectLatest { usersInfo ->
                uiState = AccountUiState(
                    parentAccount = Account(
                        id = usersInfo.parent.id,
                        name = "${usersInfo.parent.firstname} ${usersInfo.parent.lastname}",
                        isSelected = usersInfo.parent.id == usersInfo.selectedUserId
                    ),
                    childAccount = usersInfo.child?.let { child ->
                        Account(
                            id = child.id,
                            name = "${child.firstname} ${child.surname}",
                            isSelected = child.id == usersInfo.selectedUserId
                        )
                    },
                )
            }
        }
    }

    fun selectAccount(id: Int) {
        viewModelScope.launch { accountRepository.selectUser(id) }
    }

    fun signOut() {
        viewModelScope.launch {
            accountRepository.clearUsersInfo()
        }
    }

    fun retry() = uiState?.imageFile?.let { uploadImage(it) }

    fun uploadImage(imageFile: File) {
        viewModelScope.launch {
            val parentId = uiState?.parentAccount?.id ?: return@launch
            uiState = uiState?.copy(
                imageFile = imageFile,
                isLoading = true,
                isError = false
            )

            val resource = accountRepository.uploadBirthCertificate(parentId, imageFile)
            when {
                resource.payload == true -> {
                    setUserInformationUseCase(parentId)
                    uiState = uiState?.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }

                else -> {
                    uiState = uiState?.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }
}
