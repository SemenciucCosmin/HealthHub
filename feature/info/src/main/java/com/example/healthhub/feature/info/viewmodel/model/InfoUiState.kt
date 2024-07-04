package com.example.healthhub.feature.info.viewmodel.model

import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
import com.example.healthhub.data.info.model.Location

data class InfoUiState(
    val parent: Parent? = null,
    val child: Child? = null,
    val locations: List<Location> = emptyList()
)
