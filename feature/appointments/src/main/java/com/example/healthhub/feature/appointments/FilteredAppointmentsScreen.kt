package com.example.healthhub.feature.appointments

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.FilteredAppointment
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun FilteredAppointmentsScreen(
    specializationName: String,
    countyName: String,
    startDateMillis: Long,
    filteredAppointment: List<FilteredAppointment>,
    onFilteredAppointmentClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ElevatedCard {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                OverlineText(
                    text = specializationName,
                    overlineText = stringResource(R.string.lbl_specialization)
                )

                OverlineText(
                    text = countyName,
                    overlineText = stringResource(R.string.lbl_county)
                )

                OverlineText(
                    text = SimpleDateFormat.getDateInstance().format(Date(startDateMillis)),
                    overlineText = stringResource(R.string.lbl_start_date),
                    hideDivider = true
                )
            }
        }

        when {
            filteredAppointment.isEmpty() -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.lbl_no_filtered_appointments),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

            else -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(filteredAppointment) { filteredAppointment ->
                    FilteredAppointmentCard(
                        filteredAppointment = filteredAppointment,
                        onClick = onFilteredAppointmentClick
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FilteredAppointmentsScreenPreview() {
    HealthHubTheme {
        FilteredAppointmentsScreen(
            specializationName = "Specialization",
            countyName = "County",
            startDateMillis = 6031,
            filteredAppointment = emptyList(),
            onFilteredAppointmentClick = {},
        )
    }
}
