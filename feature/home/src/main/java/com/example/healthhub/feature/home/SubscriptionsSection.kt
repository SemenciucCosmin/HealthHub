package com.example.healthhub.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.ui.catalog.R

@Composable
fun SubscriptionsSection(
    subscriptions: List<Subscription>,
    onSubscriptionClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.lbl_subscriptions_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        when {
            subscriptions.isEmpty() -> Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.lbl_no_subscriptions_message),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            else -> LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(subscriptions, { it.id }) { subscription ->
                    SubscriptionCard(
                        subscription = subscription,
                        onClick = {
                            onSubscriptionClick(
                                subscription.id,
                                subscription.specializationId
                            )
                        }
                    )
                }
            }
        }
    }
}
