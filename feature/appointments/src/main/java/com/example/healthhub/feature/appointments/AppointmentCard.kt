package com.example.healthhub.feature.appointments

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onCancelClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(16.dp)
        ) {
            OverlineText(
                text = stringResource(R.string.lbl_medic_title, appointment.medic.name),
                overlineText = stringResource(R.string.lbl_medic_name)
            )

            OverlineText(
                text = appointment.specialization.name,
                overlineText = stringResource(R.string.lbl_specialization)
            )

            Row {
                // Update date formatting to include time
                val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                val date = dateFormat.format(Date(appointment.startDate))

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = date,
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

            if (appointment.isCancelable) {
                IconTextButton(
                    text = stringResource(R.string.lbl_cancel_appointment),
                    icon = painterResource(R.drawable.ic_checked),
                    onClick = { onCancelClick(appointment.id) }
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
            appointment = appointment,
            onCancelClick = {}
        )
    }
}
