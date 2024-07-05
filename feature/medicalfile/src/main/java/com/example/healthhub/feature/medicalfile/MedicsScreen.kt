package com.example.healthhub.feature.medicalfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.data.util.BLANK
import com.example.healthhub.data.util.getStringWithBullet
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.Menu
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.model.MenuItem
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import kotlinx.coroutines.launch

private const val ALL_MEDICS_PAGE = 0
private const val MEDICS_BY_ID_PAGE = 1
private const val PAGE_COUNT = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicsScreen(
    allMedics: List<Medic>,
    medicsById: List<Medic>,
    specializations: List<Specialization>,
    selectedSpecializationId: Int?,
    onSpecializationSelected: (Int?) -> Unit,
    onMedicClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { PAGE_COUNT }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Menu(
            overlineTitle = stringResource(R.string.lbl_sort_by_specialization),
            selectedMenuItemId = selectedSpecializationId,
            onMenuItemSelected = onSpecializationSelected,
            menuItems = specializations.map {
                MenuItem(
                    id = it.id,
                    name = it.name
                )
            }
        )

        SingleChoiceSegmentedButtonRow {
            SegmentedButton(
                selected = pagerState.currentPage == ALL_MEDICS_PAGE,
                shape = MaterialTheme.shapes.small,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(ALL_MEDICS_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_all_medics))
            }

            SegmentedButton(
                selected = pagerState.currentPage == MEDICS_BY_ID_PAGE,
                shape = MaterialTheme.shapes.small,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(MEDICS_BY_ID_PAGE)
                    }
                }
            ) {
                Text(text = stringResource(R.string.lbl_your_medics))
            }
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 16.dp
        ) { pageIndex ->
            val medics = if (pageIndex == ALL_MEDICS_PAGE) allMedics else medicsById

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(medics) { medic ->
                    val specializationsDescriptions =
                        medic.specializations.joinToString(String.BLANK) {
                            it.name.getStringWithBullet()
                        }

                    val servicesDescriptions = medic.services.joinToString(String.BLANK) {
                        it.name.getStringWithBullet()
                    }

                    ElevatedCard(
                        onClick = { onMedicClick(medic.id) }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row {
                                OverlineText(
                                    modifier = Modifier.weight(0.5f),
                                    text = stringResource(R.string.lbl_medic_title, medic.name),
                                    overlineText = stringResource(R.string.lbl_medic_name)
                                )

                                OverlineText(
                                    modifier = Modifier.weight(0.5f),
                                    text = medic.ranking.toString(),
                                    overlineText = stringResource(R.string.lbl_ranking)
                                )
                            }

                            OverlineText(
                                text = medic.county.name,
                                overlineText = stringResource(R.string.lbl_location)
                            )

                            OverlineText(
                                text = specializationsDescriptions,
                                overlineText = stringResource(R.string.lbl_specializations)
                            )

                            OverlineText(
                                text = servicesDescriptions,
                                overlineText = stringResource(R.string.lbl_services),
                                hideDivider = true
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MedicsScreenPreview() {
    HealthHubTheme {
        MedicsScreen(
            allMedics = emptyList(),
            medicsById = emptyList(),
            specializations = emptyList(),
            selectedSpecializationId = null,
            onSpecializationSelected = {},
            onMedicClick = {}
        )
    }
}