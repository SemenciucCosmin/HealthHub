package com.example.healthhub.info.viewmodel.model

import com.example.healthhub.data.info.model.Location

data class InfoUiState(
    val locations: List<Location> = emptyList()
)
