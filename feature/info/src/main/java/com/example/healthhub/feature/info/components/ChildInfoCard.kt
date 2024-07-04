package com.example.healthhub.feature.info.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.ChildPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun ChildInfoCard(
    child: Child,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.firstname,
                    overlineText = stringResource(R.string.lbl_firstname)
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.surname,
                    overlineText = stringResource(R.string.lbl_lastname)
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.fatherFirstname,
                    overlineText = stringResource(R.string.lbl_father_firstname)
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.fatherSurname,
                    overlineText = stringResource(R.string.lbl_father_lastname)
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.motherFirstName,
                    overlineText = stringResource(R.string.lbl_mother_firstname)
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.motherSurname,
                    overlineText = stringResource(R.string.lbl_mother_lastname)
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.cnp,
                    overlineText = stringResource(R.string.lbl_security_code),
                    hideDivider = true
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = child.dateOfBirth,
                    overlineText = stringResource(R.string.lbl_date_of_birth),
                    hideDivider = true
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ChildInfoCardPreview(
    @PreviewParameter(ChildPreviewParameterProvider::class)
    child: Child,
) {
    HealthHubTheme {
        ChildInfoCard(
            child = child
        )
    }
}
