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
import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.account.model.UserPreviewParameterProvider
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun UserInfoCard(
    user: User,
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
                    text = user.firstname,
                    overlineText = stringResource(R.string.lbl_firstname)
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = user.lastname,
                    overlineText = stringResource(R.string.lbl_lastname)
                )
            }

            OverlineText(
                text = user.email,
                overlineText = stringResource(R.string.lbl_email)
            )

            OverlineText(
                text = user.nationality,
                overlineText = stringResource(R.string.lbl_nationality)
            )


            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = user.cnp,
                    overlineText = stringResource(R.string.lbl_security_code)
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = user.series,
                    overlineText = stringResource(R.string.lbl_series)
                )
            }

            Row {
                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = user.dateOfBirth,
                    overlineText = stringResource(R.string.lbl_date_of_birth),
                    hideDivider = true
                )

                OverlineText(
                    modifier = Modifier.weight(0.5f),
                    text = user.gender,
                    overlineText = stringResource(R.string.lbl_gender),
                    hideDivider = true
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UserInfoCardPreview(
    @PreviewParameter(UserPreviewParameterProvider::class)
    user: User,
) {
    HealthHubTheme {
        UserInfoCard(
            user = user
        )
    }
}
