package com.example.healthhub.feature.info.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthhub.data.account.model.User
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.OverlineText
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun UserInfoSection(
    user: User,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column {
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
private fun UserInfoSectionPreview() {
    HealthHubTheme {
        UserInfoSection(
            user = User(
                id = 1,
                email = "cosminsemenciuc@gmail.com",
                cnp = "123456789",
                series = "SV",
                lastname = "Cosmin",
                firstname = "Semenciuc",
                nationality = "Romania",
                dateOfBirth = "05-11-2000",
                gender = "Masculine"
            )
        )
    }
}
