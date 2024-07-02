package com.example.healthhub.ui.navigation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import com.example.healthhub.ui.navigation.model.NavigationButtonType

@Composable
fun NavigationButtonsGrid(
    buttons: List<NavigationButtonType>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(buttons) {
            NavigationButton(type = it)
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NavigationButtonsGridPreview() {
    HealthHubTheme {
        NavigationButtonsGrid(
            buttons = listOf(
                NavigationButtonType.Account,
                NavigationButtonType.Location,
            )
        )
    }
}
