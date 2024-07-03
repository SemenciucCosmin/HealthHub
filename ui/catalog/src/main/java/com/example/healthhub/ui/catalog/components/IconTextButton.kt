package com.example.healthhub.ui.catalog.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun IconTextButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewIconTextButton() {
    HealthHubTheme {
        IconTextButton(
            text = "Add",
            icon = painterResource(R.drawable.ic_add),
            onClick = {}
        )
    }
}
