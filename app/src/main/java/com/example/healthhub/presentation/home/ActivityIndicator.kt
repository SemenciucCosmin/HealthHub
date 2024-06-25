package com.example.healthhub.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.R
import com.example.healthhub.presentation.theme.HealthHubTheme

@Composable
fun ActivityIndicator(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val activityColor = if (isActive) Color(0xFF43A047) else Color(0xFFC62828)
    val activityLabelRes = if (isActive) R.string.lbl_active else R.string.lbl_inactive

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(activityColor)
        )

        Text(
            text = stringResource(activityLabelRes),
            color = activityColor
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivityIndicatorActivePreview() {
    HealthHubTheme {
        ActivityIndicator(isActive = true)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActivityIndicatorInactivePreview() {
    HealthHubTheme {
        ActivityIndicator(isActive = false)
    }
}