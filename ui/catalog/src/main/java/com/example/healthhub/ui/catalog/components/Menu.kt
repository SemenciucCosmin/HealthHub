package com.example.healthhub.ui.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.model.MenuItem
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(
    overlineTitle: String,
    selectedMenuItemId: Int?,
    menuItems: List<MenuItem>,
    onMenuItemSelected: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val iconRes = if (expanded) R.drawable.ic_up else R.drawable.ic_down
    val menuItemName = menuItems.firstOrNull { it.id == selectedMenuItemId }?.name

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Text(
            text = overlineTitle,
            style = MaterialTheme.typography.labelMedium
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = menuItemName ?: stringResource(R.string.lbl_none),
                onValueChange = {},
                readOnly = true,
                shape = MaterialTheme.shapes.small,
                trailingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.lbl_none)) },
                    onClick = {
                        expanded = false
                        onMenuItemSelected(null)
                    },
                )

                menuItems.forEach { menuItem ->
                    DropdownMenuItem(
                        text = { Text(text = menuItem.name) },
                        onClick = {
                            expanded = false
                            onMenuItemSelected(menuItem.id)
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MenuPreview() {
    HealthHubTheme {
        Menu(
            overlineTitle = "Title",
            selectedMenuItemId = 0,
            menuItems = emptyList(),
            onMenuItemSelected = {}
        )
    }
}