package com.example.healthhub.feature.appointments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.compose.getKoin

@Composable
fun FilteredAppointmentsRoute(
    specializationName: String,
    countyName: String,
    startDateMillis: Long,
) {
    val koin = getKoin()
    val viewModel = koin.getScope(AppointmentsScope.ID).get<AppointmentsViewModel>()
    val navController = LocalNavController.current

    when {
        viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())
        viewModel.uiState.isError -> ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRetry = {
                viewModel.filterAppointments(
                    specializationName = specializationName,
                    countyName = countyName,
                    startDateMillis = startDateMillis
                )
            }
        )

        else -> FilteredAppointmentsScreen(
            specializationName = specializationName,
            countyName = countyName,
            startDateMillis = startDateMillis,
            filteredAppointment = viewModel.uiState.filteredAppointments,
            onFilteredAppointmentClick = {
                viewModel.selectFilteredAppointment(it)
                navController.navigate(NavDestination.FilteredAppointmentDetails)
            }
        )
    }
}
