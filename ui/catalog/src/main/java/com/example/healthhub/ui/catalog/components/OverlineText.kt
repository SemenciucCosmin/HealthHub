package com.example.healthhub.ui.catalog.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun OverlineText(
    text: String,
    overlineText: String,
    modifier: Modifier = Modifier,
    hideDivider: Boolean = false
) {
    Column(modifier = modifier) {
        ListItem(
            overlineContent = {
                Text(text = overlineText)
            },
            headlineContent = {
                Text(text = text)
            },
            colors = ListItemDefaults.colors(
                overlineColor = MaterialTheme.colorScheme.onBackground
            )
        )

        if (!hideDivider) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OverlineTextPreview() {
    HealthHubTheme {
        OverlineText(
            text = "Text",
            overlineText = "Overline"
        )
    }
}
