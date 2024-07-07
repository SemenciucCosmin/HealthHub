package com.example.healthhub.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.home.model.SubscriptionDetails
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun SubscriptionDetailsScreen(
    subscriptionDetails: SubscriptionDetails,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        ElevatedCard {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                OverlineText(
                    overlineText = stringResource(R.string.lbl_subscription_status),
                    text = if (subscriptionDetails.active) {
                        stringResource(R.string.lbl_active)
                    } else {
                        stringResource(R.string.lbl_inactive)
                    }
                )

                Row {
                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.name,
                        overlineText = stringResource(R.string.lbl_subscription_name),
                    )

                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.specializationDescription,
                        overlineText = stringResource(R.string.lbl_subscription_description),
                    )
                }

                Row {
                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.specializationName,
                        overlineText = stringResource(R.string.lbl_specialization_name),
                    )

                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.specializationDescription,
                        overlineText = stringResource(R.string.lbl_specialization_description),
                    )
                }

                Row {
                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.pricePerMonth.toString(),
                        overlineText = stringResource(R.string.lbl_price_per_month),
                        hideDivider = true
                    )

                    OverlineText(
                        modifier = Modifier.weight(0.5f),
                        text = subscriptionDetails.period.toString(),
                        overlineText = stringResource(R.string.lbl_period),
                        hideDivider = true
                    )
                }
            }
        }

        when {
            subscriptionDetails.services.isEmpty() -> Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.lbl_no_services),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(subscriptionDetails.services) {
                        ServiceCard(service = it)
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SubscriptionDetailsScreenPreview() {
    HealthHubTheme {
        SubscriptionDetailsScreen(
            subscriptionDetails = SubscriptionDetails(
                id = 2182,
                name = "Elijah Wong",
                active = false,
                pricePerMonth = 20.21,
                period = 3263,
                specializationId = 6874,
                specializationName = "Pauline Castaneda",
                specializationDescription = "cetero",
                services = listOf()
            )
        )
    }
}
