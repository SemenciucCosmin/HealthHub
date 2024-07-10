package com.example.healthhub.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.authentication.AuthenticationScreen
import com.example.healthhub.feature.authentication.EmailValidationScreen
import com.example.healthhub.feature.authentication.IdValidationScreen
import com.example.healthhub.feature.authentication.viewmodel.AuthenticationViewModel
import com.example.healthhub.feature.authentication.viewmodel.model.AuthenticationUiState
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.components.ErrorScreen
import com.example.healthhub.ui.catalog.components.LoadingScreen
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationActivity : ComponentActivity() {
    private val viewModel: AuthenticationViewModel by viewModel()
    private val getUsersInfoUseCase: GetUsersInfoUseCase by inject()

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
                val emailValidationToast = stringResource(R.string.lbl_email_validation_toast)

                Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { padding ->
                    when {
                        viewModel.uiState.isLoading -> {
                            if (viewModel.uiState.showToast) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(
                                        32.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(padding)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(40.dp),
                                        strokeWidth = 3.dp
                                    )

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = emailValidationToast,
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            } else {
                                LoadingScreen(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(padding)
                                )
                            }
                        }

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
                                        userEmail = viewModel.uiState.email,
                                        userPassword = viewModel.uiState.password,
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

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            getUsersInfoUseCase().filterNotNull().collectLatest { _ ->
                MainActivity.startActivity(this@AuthenticationActivity)
                this@AuthenticationActivity.finish()
            }
        }
    }

    companion object {
        fun startActivity(activity: MainActivity) {
            val intent = Intent(activity, AuthenticationActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }

            activity.startActivity(intent)
            activity.finish()
        }
    }
}
