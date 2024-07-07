package com.example.healthhub.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.appointments.model.Service
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun ServiceCard(
    service: Service,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(16.dp)
        ) {
            OverlineText(
                text = service.name,
                overlineText = stringResource(R.string.lbl_service_name),
            )

            OverlineText(
                text = service.description,
                overlineText = stringResource(R.string.lbl_service_description),
            )

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = service.price.toString(),
                    overlineText = stringResource(R.string.lbl_price),
                    hasStrike = true,
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = service.discountedPrice.toString(),
                    overlineText = stringResource(R.string.lbl_discounted_price),
                )
            }

            OverlineText(
                text = service.duration.toString(),
                overlineText = stringResource(R.string.lbl_duration),
                hideDivider = true,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ServiceCardPreview() {
    HealthHubTheme {
        ServiceCard(
            service = Service(
                id = 4674,
                name = "Jack Yang",
                description = "adolescens",
                price = 8.9f,
                discountedPrice = 10.11f,
                duration = 8887
            )
        )
    }
}
