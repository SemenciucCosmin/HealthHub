package com.example.healthhub.feature.appointments

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun AppointmentCard(
    appointment: Appointment,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column {
            OverlineText(
                text = stringResource(R.string.lbl_medic_title, appointment.medic.name),
                overlineText = stringResource(R.string.lbl_medic_name)
            )

            OverlineText(
                text = appointment.specialization.name,
                overlineText = stringResource(R.string.lbl_specialization)
            )

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = appointment.startDate,
                    overlineText = stringResource(R.string.lbl_date),
                    hideDivider = true
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = appointment.location.name,
                    overlineText = stringResource(R.string.lbl_location),
                    hideDivider = true
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppointmentCardPreview(
    @PreviewParameter(AppointmentPreviewParameterProvider::class)
    appointment: Appointment
) {
    HealthHubTheme {
        AppointmentCard(
            appointment = appointment
        )
    }
}
