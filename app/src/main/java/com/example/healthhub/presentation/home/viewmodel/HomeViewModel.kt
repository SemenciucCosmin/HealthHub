package com.example.healthhub.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthhub.data.home.repository.HomeRepository
import com.example.healthhub.presentation.home.viewmodel.model.HomeUiState

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set
}
