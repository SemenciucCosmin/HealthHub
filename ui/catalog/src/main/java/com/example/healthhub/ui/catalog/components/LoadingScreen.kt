package com.example.healthhub.ui.catalog.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import com.example.healthhub.ui.catalog.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.lbl_loading)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            strokeWidth = 3.dp
        )

        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingScreenPreview() {
    HealthHubTheme {
        LoadingScreen()
    }
}