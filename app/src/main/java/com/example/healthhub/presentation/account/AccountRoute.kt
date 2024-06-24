package com.example.healthhub.presentation.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.healthhub.R
import com.example.healthhub.presentation.account.viewmodel.AccountViewModel
import com.example.healthhub.presentation.app.AuthenticationActivity
import com.example.healthhub.presentation.app.MainActivity
import com.example.healthhub.presentation.ui.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountRoute() {
    val mainActivity = LocalContext.current as MainActivity
    val viewModel = koinViewModel<AccountViewModel>()
    var showSignOutDialog by remember { mutableStateOf(false) }
    when (val uiState = viewModel.uiState) {
        null -> LoadingScreen(modifier = Modifier.fillMaxSize())
        else -> AccountScreen(
            parentAccount = uiState.parentAccount,
            childAccount = uiState.childAccount,
            onSelectAccountClick = viewModel::selectAccount,
            onAddAccountClick = {},
            onSignOutClick = { showSignOutDialog = true },
            modifier = Modifier.fillMaxSize()
        )
    }

    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            title = {
                Text(text = stringResource(R.string.lbl_sign_out_dialog_title))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.signOut()
                        AuthenticationActivity.startActivity(mainActivity)
                        mainActivity.finish()
                    }
                ) {
                    Text(text = stringResource(R.string.lbl_yes_action))
                }
            },
            dismissButton = {
                TextButton(onClick = { showSignOutDialog = false }) {
                    Text(text = stringResource(R.string.lbl_cancel_action))
                }
            }
        )
    }
}
