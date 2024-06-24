package com.example.healthhub.presentation.account

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.R
import com.example.healthhub.presentation.account.viewmodel.model.Account
import com.example.healthhub.presentation.theme.HealthHubTheme

@Composable
fun AccountScreen(
    parentAccount: Account,
    onSelectAccountClick: (Int) -> Unit,
    onAddAccountClick: () -> Unit,
    modifier: Modifier = Modifier,
    childAccount: Account? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.lbl_my_accounts),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        AccountItem(
            account = parentAccount,
            onClick = { onSelectAccountClick(parentAccount.id) }
        )

        when {
            childAccount != null -> AccountItem(
                account = childAccount,
                onClick = { onSelectAccountClick(childAccount.id) }
            )

            else -> ElevatedCard(onClick = onAddAccountClick) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.ic_mock),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(R.string.lbl_add_child_account),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AccountScreenPreview() {
    HealthHubTheme {
        AccountScreen(
            childAccount = null,
            onSelectAccountClick = {},
            onAddAccountClick = {},
            parentAccount = Account(
                id = 6153,
                name = "Chad Wolfe",
                isSelected = true
            )
        )
    }
}
