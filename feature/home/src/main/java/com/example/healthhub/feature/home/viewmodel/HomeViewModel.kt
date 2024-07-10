package com.example.healthhub.feature.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.home.repository.HomeRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.home.viewmodel.model.HomeUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUsersInfoUseCase: GetUsersInfoUseCase,
    private val homeRepository: HomeRepository
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            getUsersInfoUseCase().filterNotNull().collectLatest { usersInfo ->
                val resource = homeRepository.getUserSubscriptions(usersInfo.selectedUserId)
                val subscriptions = resource.payload ?: emptyList()
                val sortedSubscriptions = subscriptions.groupBy { !it.active }.values.flatten()
                uiState = uiState.copy(userSubscriptions = sortedSubscriptions)
            }
        }

        viewModelScope.launch {
            val resource = homeRepository.getAllSubscriptions()
            val subscriptions = resource.payload ?: emptyList()
            uiState = uiState.copy(allSubscriptions = subscriptions)
        }
    }
}
