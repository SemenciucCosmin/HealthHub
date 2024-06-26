package com.example.healthhub.info.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.info.repository.InfoRepository
import com.example.healthhub.info.viewmodel.model.InfoUiState
import kotlinx.coroutines.launch

class InfoViewModel(private val infoRepository: InfoRepository) : ViewModel() {
    var uiState by mutableStateOf(InfoUiState())
        private set

    init {
        viewModelScope.launch {
            infoRepository.getLocations().payload?.let { locations ->
                uiState = uiState.copy(
                    locations = locations
                )
            }
        }
    }
}
