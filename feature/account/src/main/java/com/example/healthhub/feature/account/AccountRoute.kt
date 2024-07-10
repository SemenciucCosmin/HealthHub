package com.example.healthhub.feature.account

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.healthhub.feature.account.model.AccountDialog
import com.example.healthhub.feature.account.viewmodel.AccountViewModel
import com.example.healthhub.network.resource.Status
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountRoute(onSignOut: () -> Unit) {
    val context = LocalContext.current
    val viewModel = koinViewModel<AccountViewModel>()
    var showDialog by remember { mutableStateOf(false) }
    var accountDialog by remember { mutableStateOf(AccountDialog.SIGN_OUT) }
    val navController = LocalNavController.current
    var text by remember { mutableStateOf("") }
    val emailUpdateSuccessMessage = stringResource(R.string.lbl_email_update_success_toast)
    val emailUpdateErrorMessage = stringResource(R.string.lbl_email_update_error_toast)
    val passwordUpdateSuccessMessage = stringResource(R.string.lbl_password_update_success_toast)
    val passwordUpdateErrorMessage = stringResource(R.string.lbl_password_update_error_toast)

    when (val uiState = viewModel.uiState) {
        null -> LoadingScreen(modifier = Modifier.fillMaxSize())
        else -> {
            AccountScreen(
                parentAccount = uiState.parentAccount,
                childAccount = uiState.childAccount,
                onSelectAccountClick = viewModel::selectAccount,
                onAddAccountClick = { navController.navigate(NavDestination.AddChild) },
                onChangeEmailClick = {
                    accountDialog = AccountDialog.CHANGE_EMAIL
                    showDialog = true
                },
                onChangePasswordClick = {
                    accountDialog = AccountDialog.CHANGE_PASSWORD
                    showDialog = true
                },
                onSignOutClick = {
                    accountDialog = AccountDialog.SIGN_OUT
                    showDialog = true
                },
                modifier = Modifier.fillMaxSize()
            )

            when (uiState.emailUpdateStatus) {
                Status.Empty -> Unit
                Status.Success -> {
                    Toast.makeText(context, emailUpdateSuccessMessage, Toast.LENGTH_SHORT).show()
                    viewModel.resetStatus()
                }

                else -> {
                    Toast.makeText(context, emailUpdateErrorMessage, Toast.LENGTH_SHORT).show()
                    viewModel.resetStatus()
                }
            }

            when (uiState.passwordUpdateStatus) {
                Status.Empty -> Unit
                Status.Success -> {
                    Toast.makeText(context, passwordUpdateSuccessMessage, Toast.LENGTH_SHORT).show()
                    viewModel.resetStatus()
                }

                else -> {
                    Toast.makeText(context, passwordUpdateErrorMessage, Toast.LENGTH_SHORT).show()
                    viewModel.resetStatus()
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                when (accountDialog) {
                    AccountDialog.SIGN_OUT -> {
                        Text(text = stringResource(accountDialog.titleRes))
                    }

                    AccountDialog.CHANGE_EMAIL,
                    AccountDialog.CHANGE_PASSWORD -> {
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            isError = text.isBlank(),
                            placeholder = { Text(text = stringResource(accountDialog.titleRes)) },
                        )
                    }
                }
            },
            text = {
                Text(text = stringResource(accountDialog.messageRes))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        when (accountDialog) {
                            AccountDialog.CHANGE_EMAIL -> {
                                if (text.isNotBlank()) {
                                    showDialog = false
                                    viewModel.changeEmail(text)
                                    text = ""
                                }
                            }

                            AccountDialog.CHANGE_PASSWORD -> {
                                if (text.isNotBlank()) {
                                    showDialog = false
                                    viewModel.changePassword(text)
                                    text = ""
                                }
                            }

                            AccountDialog.SIGN_OUT -> {
                                showDialog = false
                                viewModel.signOut()
                                onSignOut()
                            }
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.lbl_yes_action))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(R.string.lbl_cancel_action))
                }
            }
        )
    }
}
