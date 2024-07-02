package com.example.healthhub.feature.medicalfile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthhub.feature.medicalfile.viewmodel.MedicalFileViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MedicsRoute() {
    val viewModel = koinViewModel<MedicalFileViewModel>()

    when {
        viewModel.uiState.isError -> ErrorScreen(
            modifier = Modifier.fillMaxWidth(),
            onRetry = viewModel::retry
        )

        else -> {}
    }
}
