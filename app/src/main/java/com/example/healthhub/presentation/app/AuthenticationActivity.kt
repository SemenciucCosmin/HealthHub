package com.example.healthhub.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.healthhub.presentation.authentication.AuthenticationScreen
import com.example.healthhub.presentation.authentication.EmailValidationScreen
import com.example.healthhub.presentation.authentication.IdValidationScreen
import com.example.healthhub.presentation.authentication.viewmodel.AuthenticationViewModel
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState
import com.example.healthhub.presentation.theme.HealthHubTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationActivity : ComponentActivity() {
    private val viewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthHubTheme {
                when (viewModel.uiState.authenticationStep) {
                    AuthenticationUiState.Step.AUTHENTICATION -> {
                        AuthenticationScreen(
                            modifier = Modifier.fillMaxSize(),
                            authenticationStatus = viewModel.uiState.authenticationStatus,
                            onAuthenticationClick = viewModel::authenticate
                        )
                    }

                    AuthenticationUiState.Step.EMAIL_VALIDATION -> {
                        EmailValidationScreen(
                            modifier = Modifier.fillMaxSize(),
                            emailValidationStatus = viewModel.uiState.emailValidationStatus,
                            onNextStepClick = viewModel::getAccountValidationStatus
                        )
                    }

                    AuthenticationUiState.Step.ID_VALIDATION -> {
                        IdValidationScreen(
                            modifier = Modifier.fillMaxSize(),
                            idValidationStatus = viewModel.uiState.idValidationStatus,
                            onIdImageUriReady = viewModel::uploadImage
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

    override fun onResume() {
        super.onResume()
        viewModel.getAccountValidationStatus()
    }
}
