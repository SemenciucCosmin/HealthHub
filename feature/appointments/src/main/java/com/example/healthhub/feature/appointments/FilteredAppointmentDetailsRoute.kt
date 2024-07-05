package com.example.healthhub.feature.appointments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.healthhub.feature.appointments.di.AppointmentsScope
import com.example.healthhub.feature.appointments.viewmodel.AppointmentsViewModel
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.compose.getKoin

@Composable
fun FilteredAppointmentDetailsRoute() {
    val koin = getKoin()
    val viewModel = koin.getScope(AppointmentsScope.ID).get<AppointmentsViewModel>()
    val navController = LocalNavController.current
    val filteredAppointment = viewModel.uiState.filteredAppointment ?: return

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            FilteredAppointmentCard(
                filteredAppointment = filteredAppointment,
                detailed = false
            )
        }

        items(filteredAppointment.services) { service ->
            val painter = if (service in viewModel.uiState.selectedServices) {
                painterResource(R.drawable.ic_checked)
            } else {
                painterResource(R.drawable.ic_unchecked)
            }

            ElevatedCard(onClick = { viewModel.handleServiceSelection(service) }) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painter,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        text = service.name,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        item {
            ElevatedCard {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = viewModel.uiState.totalPrice.toString(),
                        overlineText = stringResource(R.string.lbl_total_price),
                        hideDivider = true
                    )

                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = viewModel.uiState.totalDuration.toString(),
                        overlineText = stringResource(R.string.lbl_total_duration),
                        hideDivider = true
                    )
                }
            }
        }

        item {
            IconTextButton(
                text = stringResource(R.string.lbl_finish),
                icon = painterResource(R.drawable.ic_checked),
                enabled = viewModel.uiState.selectedServices.isNotEmpty(),
                onClick = {
                    viewModel.finishAppointmentCreation()
                    navController.navigate(NavDestination.Home) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
