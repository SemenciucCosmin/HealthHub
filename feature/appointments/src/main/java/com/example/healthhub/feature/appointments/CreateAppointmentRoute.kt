package com.example.healthhub.feature.appointments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.compose.getKoin

@Composable
fun CreateAppointmentRoute(medicId: Int) {
    val koin = getKoin()
    val viewModel = koin.getScope(AppointmentsScope.ID).get<AppointmentsViewModel>()
    val navController = LocalNavController.current

    LaunchedEffect(medicId) {
        when (medicId) {
            Medic.INVALID_ID -> viewModel.loadDataForAppointmentCreation()
            else -> viewModel.loadPresetsByMedicId(medicId)
        }
    }

    when {
        viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())
        viewModel.uiState.isError -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = {
                when (medicId) {
                    Medic.INVALID_ID -> viewModel.loadDataForAppointmentCreation()
                    else -> viewModel.loadPresetsByMedicId(medicId)
                }
            }
        )

        else -> CreateAppointmentScreen(
            modifier = Modifier.padding(16.dp),
            specializations = viewModel.uiState.specializations,
            counties = viewModel.uiState.counties,
            onCreateAppointment = { specializationName, countyName, startDateMillis ->
                viewModel.selectFilters(
                    specializationName = specializationName,
                    countyName = countyName,
                    startDateMillis = startDateMillis
                )

                viewModel.filterAppointments()
                navController.navigate(NavDestination.FilteredAppointments)
            }
        )
    }
}
