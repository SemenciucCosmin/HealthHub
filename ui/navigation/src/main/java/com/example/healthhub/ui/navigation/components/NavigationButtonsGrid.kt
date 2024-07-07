package com.example.healthhub.ui.navigation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import com.example.healthhub.ui.navigation.model.NavigationButtonType

@Composable
fun NavigationButtonsGrid(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NavigationButton(
                type = NavigationButtonType.Account,
                modifier = Modifier.weight(0.5f)
            )
            NavigationButton(
                type = NavigationButtonType.Location,
                modifier = Modifier.weight(0.5f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NavigationButton(
                type = NavigationButtonType.Medics,
                modifier = Modifier.weight(0.5f)
            )
            NavigationButton(
                type = NavigationButtonType.FutureAppointments,
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NavigationButtonsGridPreview() {
    HealthHubTheme {
        NavigationButtonsGrid()
    }
}
