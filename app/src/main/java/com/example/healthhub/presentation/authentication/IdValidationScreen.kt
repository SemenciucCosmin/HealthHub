package com.example.healthhub.presentation.authentication

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.healthhub.R
import com.example.healthhub.data.model.Status
import com.example.healthhub.presentation.ui.LoadingScreen
import kotlinx.coroutines.launch

@Composable
fun IdValidationScreen(
    idValidationStatus: Status,
    onIdImageUriReady: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val dataErrorMessage = stringResource(R.string.lbl_email_validation_message)
    val networkErrorMessage = stringResource(R.string.lbl_network_error_message)

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
        when (idValidationStatus) {
            Status.Loading -> LoadingScreen(modifier = modifier.padding(padding))

            else -> IdValidationContent(
                modifier = modifier,
                onIdImageUriReady = onIdImageUriReady
            )
        }
    }

    LaunchedEffect(idValidationStatus) {
        when (idValidationStatus) {
            Status.DataError -> coroutineScope.launch {
                snackbarHostState.showSnackbar(message = dataErrorMessage)
            }

            Status.NetworkError -> coroutineScope.launch {
                snackbarHostState.showSnackbar(message = networkErrorMessage)
            }

            else -> Unit
        }
    }
}
