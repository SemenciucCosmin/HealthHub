package com.example.healthhub.feature.medicalfile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.medicalfile.viewmodel.MedicalFileViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

@Composable
fun MedicsRoute() {
    val koin = getKoin()
    val viewModel = koinViewModel<MedicalFileViewModel>()
    val navController = LocalNavController.current

    when {
        viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())

        viewModel.uiState.isError -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = viewModel::retry
        )

        else -> MedicsScreen(
            modifier = Modifier.padding(16.dp),
            allMedics = viewModel.uiState.filteredMedics,
            medicsById = viewModel.uiState.filteredMedicsById,
            specializations = viewModel.uiState.specializations,
            selectedSpecializationId = viewModel.uiState.selectedSpecializationId,
            onSpecializationSelected = viewModel::selectSpecialization,
            onMedicClick = { medicId ->
                AppointmentsScope.create(koin)
                navController.navigate(NavDestination.CreateAppointment(medicId))
            }
        )
    }
}
