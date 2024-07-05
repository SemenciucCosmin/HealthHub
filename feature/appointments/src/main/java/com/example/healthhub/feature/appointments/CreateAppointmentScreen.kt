package com.example.healthhub.feature.appointments

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.County
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.Menu
import com.example.healthhub.ui.catalog.model.MenuItem
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAppointmentScreen(
    specializations: List<Specialization>,
    counties: List<County>,
    filteredMedics: List<Medic>,
    selectedSpecializationId: Int?,
    selectedCountyId: Int?,
    selectedMedicId: Int?,
    selectedLocationId: Int?,
    onSelectSpecialization: (Int?) -> Unit,
    onSelectCounty: (Int?) -> Unit,
    onSelectMedic: (Int?) -> Unit,
    onSelectLocation: (Int?) -> Unit,
    onSelectStartDate: (Long) -> Unit,
    onCreateAppointment: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val selectedMedic = filteredMedics.firstOrNull { it.id == selectedMedicId }
    val formattedDate = dateState.selectedDateMillis?.let { millis ->
        SimpleDateFormat.getDateInstance().format(Date(millis))
    } ?: "Choose Date"

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.animateContentSize()
    ) {
        Menu(
            overlineTitle = stringResource(R.string.lbl_specializations),
            selectedMenuItemId = selectedSpecializationId,
            onMenuItemSelected = { specializationId ->
                specializationId?.let(onSelectSpecialization)
            },
            menuItems = specializations.map {
                MenuItem(
                    id = it.id,
                    name = it.name
                )
            }
        )

        Menu(
            overlineTitle = stringResource(R.string.lbl_counties),
            selectedMenuItemId = selectedCountyId,
            onMenuItemSelected = { countyId ->
                countyId?.let(onSelectCounty)
            },
            menuItems = counties.map {
                MenuItem(
                    id = it.id,
                    name = it.name
                )
            }
        )

        ElevatedCard(onClick = { showDialog = true }) {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = stringResource(R.string.lbl_ok_action))
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = stringResource(R.string.lbl_cancel_action))
                    }
                }
            ) {
                DatePicker(
                    state = dateState,
                    showModeToggle = true
                )
            }
        }

        if (filteredMedics.isNotEmpty()) {
            Menu(
                overlineTitle = stringResource(R.string.lbl_medics),
                selectedMenuItemId = selectedMedicId,
                onMenuItemSelected = { medicId ->
                    medicId?.let(onSelectMedic)
                },
                menuItems = filteredMedics.map {
                    MenuItem(
                        id = it.id,
                        name = it.name
                    )
                }
            )

            selectedMedic?.let { medic ->
                Menu(
                    overlineTitle = stringResource(R.string.lbl_locations),
                    selectedMenuItemId = selectedLocationId,
                    onMenuItemSelected = { locationId ->
                        locationId?.let(onSelectLocation)
                    },
                    menuItems = medic.locations.map {
                        MenuItem(
                            id = it.id,
                            name = it.name
                        )
                    }
                )
            }
        }

        IconTextButton(
            text = stringResource(R.string.lbl_create_appointment),
            icon = painterResource(R.drawable.ic_checked),
            enabled = selectedSpecializationId != null && selectedCountyId != null && dateState.selectedDateMillis != null,
            onClick = {
                val startDateMillis = dateState.selectedDateMillis ?: return@IconTextButton
                onSelectStartDate(startDateMillis)
                onCreateAppointment()
            }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateAppointmentScreenPreview() {
    HealthHubTheme {
        CreateAppointmentScreen(
            specializations = emptyList(),
            counties = emptyList(),
            filteredMedics = emptyList(),
            selectedSpecializationId = null,
            selectedCountyId = null,
            selectedMedicId = null,
            selectedLocationId = null,
            onSelectSpecialization = {},
            onSelectCounty = {},
            onSelectMedic = {},
            onSelectLocation = {},
            onSelectStartDate = {},
            onCreateAppointment = {}
        )
    }
}
