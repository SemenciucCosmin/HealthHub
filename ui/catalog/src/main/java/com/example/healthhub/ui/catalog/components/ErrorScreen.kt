package com.example.healthhub.ui.catalog.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.lbl_network_error_message),
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedButton(
            onClick = onRetry,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = stringResource(R.string.lbl_retry_action)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorScreenPreview() {
    HealthHubTheme {
        ErrorScreen(
            onRetry = {}
        )
    }
}