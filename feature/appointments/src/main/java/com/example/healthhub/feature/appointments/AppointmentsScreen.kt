package com.example.healthhub.feature.appointments

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Appointment
import com.example.healthhub.data.appointments.model.AppointmentPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import kotlinx.coroutines.launch

private const val PAST_PAGE = 0
private const val FUTURE_PAGE = 1
private const val PAGE_COUNT = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsScreen(
    pastAppointments: List<Appointment>,
    futureAppointments: List<Appointment>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { PAGE_COUNT }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        SingleChoiceSegmentedButtonRow {
            SegmentedButton(
                selected = pagerState.currentPage == PAST_PAGE,
                shape = MaterialTheme.shapes.small,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(PAST_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_past_appointments))
            }

            SegmentedButton(
                selected = pagerState.currentPage == FUTURE_PAGE,
                shape = MaterialTheme.shapes.small,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(FUTURE_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_future_appointments))
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 16.dp
        ) { pageIndex ->
            val appointments = if (pageIndex == PAST_PAGE) pastAppointments else futureAppointments
            AppointmentsPage(
                modifier = Modifier.fillMaxSize(),
                appointments = appointments
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppointmentsScreenPreview(
    @PreviewParameter(AppointmentPreviewParameterProvider::class)
    appointment: Appointment
) {
    HealthHubTheme {
        AppointmentsScreen(
            pastAppointments = List(5) { appointment },
            futureAppointments = List(5) { appointment },
        )
    }
}