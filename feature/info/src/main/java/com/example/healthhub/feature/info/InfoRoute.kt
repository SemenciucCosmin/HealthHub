package com.example.healthhub.feature.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthhub.feature.info.components.UserInfoSection
import com.example.healthhub.feature.info.viewmodel.InfoViewModel
import com.example.healthhub.ui.catalog.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoRoute() {
    val viewModel = koinViewModel<InfoViewModel>()

    when (val parent = viewModel.uiState.parent) {
        null -> LoadingScreen(Modifier.fillMaxSize())
        else -> {
            Column {
                UserInfoSection(
                    parent = parent,
                    child = viewModel.uiState.child
                )
            }
        }
    }
}
