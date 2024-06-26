package com.example.healthhub.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import com.example.healthhub.ui.catalog.util.thenIf
import com.example.healthhub.data.home.model.Subscription

@Composable
fun SubscriptionCard(
    subscription: Subscription,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Box {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = subscription.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                    ActivityIndicator(isActive = subscription.active)
                }

                Text(
                    text = stringResource(
                        R.string.lbl_subscription_price,
                        subscription.pricePerMonth
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = stringResource(R.string.lbl_subscription_period, subscription.period),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .thenIf(!subscription.active) {
                        background(Color.DarkGray.copy(alpha = 0.4f))
                    }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SubscriptionCardActivePreview() {
    HealthHubTheme {
        SubscriptionCard(
            subscription = Subscription(
                id = 5164,
                name = "Diana Skinner",
                active = true,
                pricePerMonth = 4.5,
                period = 17,
                specializationId = 7797
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SubscriptionCardInactivePreview() {
    HealthHubTheme {
        SubscriptionCard(
            subscription = Subscription(
                id = 5164,
                name = "Diana Skinner",
                active = false,
                pricePerMonth = 4.5,
                period = 17,
                specializationId = 7797
            )
        )
    }
}
