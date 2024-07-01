package com.example.healthhub.ui.navigation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.navigation.model.NavigationButtonType
import com.example.healthhub.ui.navigation.util.LocalNavController

@Composable
fun NavigationButton(
    type: NavigationButtonType,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current

    Surface(
        color = type.color,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .width(150.dp)
            .clickable { navController.navigate(type.destination) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(type.drawableRes),
                contentDescription = null
            )

            Text(
                text = stringResource(type.destination.stringRes),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NavigationButtonPreview() {
    NavigationButton(
        type = NavigationButtonType.Location
    )
}