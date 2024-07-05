package com.example.healthhub.feature.account

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
import androidx.compose.ui.res.stringResource
import com.example.healthhub.feature.account.viewmodel.AccountViewModel
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.util.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountRoute(onSignOut: () -> Unit) {
    val viewModel = koinViewModel<AccountViewModel>()
    var showSignOutDialog by remember { mutableStateOf(false) }
    val navController = LocalNavController.current

    when (val uiState = viewModel.uiState) {
        null -> LoadingScreen(modifier = Modifier.fillMaxSize())
        else -> AccountScreen(
            parentAccount = uiState.parentAccount,
            childAccount = uiState.childAccount,
            onSelectAccountClick = viewModel::selectAccount,
            onAddAccountClick = { navController.navigate(NavDestination.AddChild) },
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
                        onSignOut()
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
