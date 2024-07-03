package com.example.healthhub.feature.appointments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.compose.getKoin

@Composable
fun AppointmentsRoute() {
    val koin = getKoin()
    val viewModel = koin.getScope(AppointmentsScope.ID).get<AppointmentsViewModel>()
    val navController = LocalNavController.current

    LaunchedEffect(Unit) { viewModel.loadAppointments() }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.lbl_your_appointments),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        IconTextButton(
            text = stringResource(R.string.lbl_create_appointment),
            icon = painterResource(R.drawable.ic_add),
            onClick = { navController.navigate(NavDestination.CreateAppointment()) },
        )

        when {
            viewModel.uiState.isLoading -> LoadingScreen(Modifier.fillMaxSize())
            viewModel.uiState.isError -> ErrorScreen(
                onRetry = viewModel::loadAppointments,
                modifier = Modifier.fillMaxSize()
            )

            else -> AppointmentsScreen(
                pastAppointments = viewModel.uiState.pastAppointments,
                futureAppointments = viewModel.uiState.futureAppointments,
            )
        }
    }

    BackHandler {
        AppointmentsScope.delete(koin)
    }
}
