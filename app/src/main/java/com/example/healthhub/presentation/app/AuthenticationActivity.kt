package com.example.healthhub.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.healthhub.R
import com.example.healthhub.presentation.authentication.AuthenticationScreen
import com.example.healthhub.presentation.authentication.EmailValidationScreen
import com.example.healthhub.presentation.authentication.IdValidationScreen
import com.example.healthhub.presentation.authentication.viewmodel.AuthenticationViewModel
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState
import com.example.healthhub.presentation.theme.HealthHubTheme
import com.example.healthhub.presentation.ui.ErrorScreen
import com.example.healthhub.presentation.ui.LoadingScreen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationActivity : ComponentActivity() {
    private val viewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthHubTheme {
                val coroutineScope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                val authenticationError = stringResource(R.string.lbl_authentication_error)
                val emailValidationError = stringResource(R.string.lbl_email_validation_error)
                val idValidationError = stringResource(R.string.lbl_id_validation_error)

                Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
                    when {
                        viewModel.uiState.isLoading -> LoadingScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        )

                        viewModel.uiState.isError -> ErrorScreen(
                            onRetry = viewModel::retry,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        )

                        else -> {
                            when (viewModel.uiState.authenticationStep) {
                                AuthenticationUiState.Step.AUTHENTICATION -> {
                                    AuthenticationScreen(
                                        modifier = Modifier.fillMaxSize(),
                                        onAuthenticationClick = viewModel::authenticate
                                    )
                                }

                                AuthenticationUiState.Step.EMAIL_VALIDATION -> {
                                    EmailValidationScreen(
                                        modifier = Modifier.fillMaxSize(),
                                        onNextStepClick = viewModel::getAccountValidationStatus
                                    )
                                }

                                AuthenticationUiState.Step.ID_VALIDATION -> {
                                    IdValidationScreen(
                                        modifier = Modifier.fillMaxSize(),
                                        onIdImageFileReady = viewModel::uploadImage
                                    )
                                }

                                AuthenticationUiState.Step.AUTHENTICATION_COMPLETED -> {
                                    MainActivity.startActivity(this@AuthenticationActivity)
                                    this@AuthenticationActivity.finish()
                                }
                            }
                        }
                    }
                }

                LaunchedEffect(viewModel.uiState) {
                    when {
                        viewModel.uiState.isPasswordIncorrectError -> coroutineScope.launch {
                            snackbarHostState.showSnackbar(authenticationError)
                            viewModel.setEmptyState()
                        }

                        viewModel.uiState.isEmailValidationError -> coroutineScope.launch {
                            snackbarHostState.showSnackbar(emailValidationError)
                            viewModel.setEmptyState()
                        }

                        viewModel.uiState.isIdValidationError -> coroutineScope.launch {
                            snackbarHostState.showSnackbar(idValidationError)
                            viewModel.setEmptyState()
                        }
                    }
                }
            }
        }
    }
}
