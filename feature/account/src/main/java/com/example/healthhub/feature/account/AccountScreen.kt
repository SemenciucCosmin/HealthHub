package com.example.healthhub.feature.account

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.feature.account.viewmodel.model.Account
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.IconTextButton
import com.example.healthhub.ui.catalog.theme.HealthHubTheme

@Composable
fun AccountScreen(
    parentAccount: Account,
    onSelectAccountClick: (Int) -> Unit,
    onAddAccountClick: () -> Unit,
    onChangeEmailClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onSignOutClick: () -> Unit,
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
            icon = painterResource(R.drawable.ic_parent_profile),
            onClick = { onSelectAccountClick(parentAccount.id) }
        )

        when {
            childAccount != null -> AccountItem(
                account = childAccount,
                icon = painterResource(R.drawable.ic_child_profile),
                onClick = { onSelectAccountClick(childAccount.id) }
            )

            else -> IconTextButton(
                text = stringResource(R.string.lbl_add_child_account),
                icon = painterResource(R.drawable.ic_add),
                onClick = onAddAccountClick,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconTextButton(
            text = stringResource(R.string.lbl_change_email),
            icon = painterResource(R.drawable.ic_edit),
            onClick = onChangeEmailClick,
        )

        IconTextButton(
            text = stringResource(R.string.lbl_change_password),
            icon = painterResource(R.drawable.ic_edit),
            onClick = onChangePasswordClick,
        )

        OutlinedCard(onClick = onSignOutClick) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ic_sing_out),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Text(
                    text = stringResource(R.string.lbl_sign_out),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
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
            onChangeEmailClick = {},
            onChangePasswordClick = {},
            onSignOutClick = {},
            parentAccount = Account(
                id = 6153,
                name = "Chad Wolfe",
                isSelected = true
            )
        )
    }
}
