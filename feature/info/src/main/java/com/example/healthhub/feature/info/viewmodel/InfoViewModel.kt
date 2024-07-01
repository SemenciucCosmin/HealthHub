package com.example.healthhub.feature.info.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.info.repository.InfoRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.info.viewmodel.model.InfoUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InfoViewModel(
    private val infoRepository: InfoRepository,
    private val getUsersInfoUseCase: GetUsersInfoUseCase
) : ViewModel() {
    var uiState by mutableStateOf(InfoUiState())
        private set

    init {
        viewModelScope.launch {
            getUsersInfoUseCase().collectLatest { usersInfo ->
                uiState = uiState.copy(
                    parent = usersInfo.parent,
                    child = usersInfo.child
                )
            }
        }

        viewModelScope.launch {
            infoRepository.getLocations().payload?.let { locations ->
                uiState = uiState.copy(
                    locations = locations
                )
            }
        }
    }
}
