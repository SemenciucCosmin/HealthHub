package com.example.healthhub.ui.catalog.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun OverlineText(
    text: String,
    overlineText: String,
    modifier: Modifier = Modifier,
    hideDivider: Boolean = false,
    hasStrike: Boolean = false,
) {
    val textStyle = if (hasStrike) {
        MaterialTheme.typography.titleMedium.copy(textDecoration = TextDecoration.LineThrough)
    } else {
        MaterialTheme.typography.titleMedium
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {

        Text(
            text = overlineText,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (!hideDivider) HorizontalDivider()
    }
}

@Preview(showBackground = true)
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
