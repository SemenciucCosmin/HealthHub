package com.example.healthhub.feature.medicalfile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun PastAppointmentsSection(
    appointments: List<Appointment>,
    onCreateAppointmentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        IconTextButton(
            text = stringResource(R.string.lbl_create_appointment),
            icon = painterResource(R.drawable.ic_add),
            onClick = onCreateAppointmentClick,
        )

        Text(
            text = stringResource(R.string.lbl_past_appointments),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        when {
            appointments.isEmpty() -> Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.lbl_no_appointments_message),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )

            else -> LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(appointments) { appointment ->
                    ElevatedCard(modifier = modifier) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = modifier.padding(16.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                                OverlineText(
                                    overlineText = stringResource(R.string.lbl_medic_name),
                                    text = stringResource(
                                        R.string.lbl_medic_title,
                                        appointment.medic.name
                                    )
                                )

                                OverlineText(
                                    text = appointment.specialization.name,
                                    overlineText = stringResource(R.string.lbl_specialization)
                                )
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                                OverlineText(
                                    text = appointment.startDate,
                                    overlineText = stringResource(R.string.lbl_date),
                                    hideDivider = true
                                )

                                OverlineText(
                                    text = appointment.location.name,
                                    overlineText = stringResource(R.string.lbl_location),
                                    hideDivider = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PastAppointmentsSectionPreview(
    @PreviewParameter(AppointmentPreviewParameterProvider::class)
    appointment: Appointment
) {
    HealthHubTheme {
        PastAppointmentsSection(
            appointments = List(5) { appointment },
            onCreateAppointmentClick = {}
        )
    }
}
