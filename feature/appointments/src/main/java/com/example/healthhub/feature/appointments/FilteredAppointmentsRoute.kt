package com.example.healthhub.feature.appointments

import androidx.compose.runtime.Composable
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
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

}
