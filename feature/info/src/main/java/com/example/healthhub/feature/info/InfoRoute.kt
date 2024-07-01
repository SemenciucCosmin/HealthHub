package com.example.healthhub.feature.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.info.components.UserInfoSection
import com.example.healthhub.feature.info.viewmodel.InfoViewModel
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.components.NavigationButton
import com.example.healthhub.ui.navigation.model.NavigationButtonType
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoRoute() {
    val viewModel = koinViewModel<InfoViewModel>()
    val navigationButtonTypes = listOf(
        NavigationButtonType.Account,
        NavigationButtonType.Location,
    )

    when (val parent = viewModel.uiState.parent) {
        null -> LoadingScreen(Modifier.fillMaxSize())
        else -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                UserInfoSection(
                    parent = parent,
                    child = viewModel.uiState.child
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(navigationButtonTypes) {
                        NavigationButton(type = it)
                    }
                }
            }
        }
    }
}
