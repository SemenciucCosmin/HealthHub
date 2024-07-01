package com.example.healthhub.feature.info.viewmodel.model

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.info.model.Location

data class InfoUiState(
    val parent: User? = null,
    val child: User? = null,
    val locations: List<Location> = emptyList()
)
