package com.example.healthhub.feature.medicalfile

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
import com.example.healthhub.data.appointments.model.Specialization
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecializationsMenu(
    selectedSpecializationId: Int?,
    specializations: List<Specialization>,
    onSpecializationSelected: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val iconRes = if (expanded) R.drawable.ic_up else R.drawable.ic_down
    val specializationName = specializations.firstOrNull { it.id == selectedSpecializationId }?.name

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.lbl_sort_by_specialization),
            style = MaterialTheme.typography.labelMedium
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = specializationName ?: stringResource(R.string.lbl_all),
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
                    text = { Text(text = stringResource(R.string.lbl_all)) },
                    onClick = {
                        expanded = false
                        onSpecializationSelected(null)
                    },
                )

                specializations.forEach { specialization ->
                    DropdownMenuItem(
                        text = { Text(text = specialization.name) },
                        onClick = {
                            expanded = false
                            onSpecializationSelected(specialization.id)
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SpecializationsMenuPreview() {
    HealthHubTheme {
        SpecializationsMenu(
            selectedSpecializationId = null,
            specializations = emptyList(),
            onSpecializationSelected = {}
        )
    }
}