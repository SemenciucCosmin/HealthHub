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
import com.example.healthhub.data.appointments.model.FilteredAppointment
import com.example.healthhub.data.appointments.model.FilteredAppointmentPreviewParameterProvider
import com.example.healthhub.data.util.BLANK
import com.example.healthhub.data.util.getStringWithBullet
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun FilteredAppointmentCard(
    filteredAppointment: FilteredAppointment,
    detailed: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    val date = SimpleDateFormat.getDateInstance().format(Date(filteredAppointment.dateMillis))
    val servicesDescriptions = filteredAppointment.services.joinToString(String.BLANK) {
        it.name.getStringWithBullet()
    }

    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    overlineText = stringResource(R.string.lbl_medic_name),
                    text = stringResource(
                        R.string.lbl_medic_title,
                        filteredAppointment.doctorFullName
                    )
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = filteredAppointment.ranking.toString(),
                    overlineText = stringResource(R.string.lbl_ranking)
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = filteredAppointment.county.name,
                    overlineText = stringResource(R.string.lbl_county),
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = filteredAppointment.location.name,
                    overlineText = stringResource(R.string.lbl_location),
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = filteredAppointment.specializationName,
                    overlineText = stringResource(R.string.lbl_specialization),
                    hideDivider = !detailed
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = date,
                    overlineText = stringResource(R.string.lbl_date),
                    hideDivider = !detailed
                )
            }

            if (detailed) {
                OverlineText(
                    text = servicesDescriptions,
                    overlineText = stringResource(R.string.lbl_services),
                    hideDivider = true
                )

                IconTextButton(
                    text = stringResource(R.string.lbl_select_appointment),
                    icon = painterResource(R.drawable.ic_checked),
                    onClick = { onClick(filteredAppointment.id) }
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FilteredAppointmentCardPreview(
    @PreviewParameter(FilteredAppointmentPreviewParameterProvider::class)
    filteredAppointment: FilteredAppointment
) {
    HealthHubTheme {
        FilteredAppointmentCard(
            filteredAppointment = filteredAppointment,
            detailed = true,
            onClick = {}
        )
    }
}
