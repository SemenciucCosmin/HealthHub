package com.example.healthhub.feature.medicalfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.medicalfile.viewmodel.MedicalFileViewModel
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.components.NavigationButtonsGrid
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.model.NavigationButtonType
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun MedicalFileRoute() {
    val viewModel = koinViewModel<MedicalFileViewModel>()
    val navController = LocalNavController.current

    when {
        viewModel.uiState.isLoading -> LoadingScreen(modifier = Modifier.fillMaxSize())

        else -> Column(
            verticalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            PastAppointmentsSection(
                appointments = viewModel.uiState.pastAppointments,
                onCreateAppointmentClick = {
                    navController.navigate(NavDestination.CreateAppointment)
                }
            )

            NavigationButtonsGrid(
                buttons = listOf(
                    NavigationButtonType.Account,
                    NavigationButtonType.Location,
                    NavigationButtonType.Medics,
                )
            )
        }
    }
}
