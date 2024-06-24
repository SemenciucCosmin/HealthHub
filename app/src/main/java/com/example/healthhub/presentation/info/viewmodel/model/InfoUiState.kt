package com.example.healthhub.presentation.info.viewmodel.model

import com.example.healthhub.data.info.model.Location

data class InfoUiState(
    val locations: List<Location> = emptyList()
)
