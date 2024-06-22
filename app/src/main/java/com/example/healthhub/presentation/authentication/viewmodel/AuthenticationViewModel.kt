package com.example.healthhub.presentation.authentication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState

class AuthenticationViewModel : ViewModel() {
    var uiState by mutableStateOf(AuthenticationUiState())
        private set
}
