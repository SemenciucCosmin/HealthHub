package com.example.healthhub.feature.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.home.repository.HomeRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.home.viewmodel.model.SubscriptionDetailsUiState
import com.example.healthhub.network.resource.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SubscriptionDetailsViewModel(
    private val getUsersInfoUseCase: GetUsersInfoUseCase,
    private val homeRepository: HomeRepository
) : ViewModel() {
    var uiState by mutableStateOf(SubscriptionDetailsUiState())
        private set

    fun loadSubscriptionDetails(
        subscriptionId: Int,
        specializationId: Int,
    ) {
        viewModelScope.launch {
            getUsersInfoUseCase().filterNotNull().collectLatest { usersInfo ->
                val resource = homeRepository.getSubscriptionDetails(
                    userId = usersInfo.selectedUserId,
                    subscriptionId = subscriptionId,
                    specializationId = specializationId
                )

                uiState = uiState.copy(
                    subscriptionDetails = resource.payload,
                    isError = resource.status != Status.Success,
                    isLoading = false
                )
            }
        }
    }

    fun retry(subscriptionId: Int, specializationId: Int) {
        uiState = uiState.copy(isLoading = true)
        loadSubscriptionDetails(subscriptionId, specializationId)
    }

    fun addSubscription(subscriptionId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(subscriptionAdditionStatus = Status.Loading)
            val userId = getUsersInfoUseCase().firstOrNull()?.parent?.id ?: return@launch
            val resource = homeRepository.addSubscription(
                userId = userId,
                subscriptionId = subscriptionId,
                validFromDateMillis = System.currentTimeMillis()
            )

            uiState = uiState.copy(subscriptionAdditionStatus = resource.status)
        }
    }
}
