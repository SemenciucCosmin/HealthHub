package com.example.healthhub.presentation.account.viewmodel

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.R
import com.example.healthhub.presentation.account.Account
import com.example.healthhub.presentation.theme.HealthHubTheme

@Composable
fun AccountItem(
    account: Account,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_mock),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = account.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = stringResource(R.string.lbl_patient_code, account.id),
                    style = MaterialTheme.typography.titleSmall
                )
            }

            val selectedPainter = when (account.isSelected) {
                true -> painterResource(R.drawable.ic_mock)
                false -> painterResource(R.drawable.ic_mock)
            }

            Image(
                painter = selectedPainter,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AccountItemPreview() {
    HealthHubTheme {
        AccountItem(
            onClick = {},
            modifier = Modifier,
            account = Account(
                id = 6153,
                name = "Chad Wolfe",
                isSelected = true
            )
        )
    }
}
