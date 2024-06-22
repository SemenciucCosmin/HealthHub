package com.example.healthhub.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.healthhub.presentation.authentication.AuthenticationScreen
import com.example.healthhub.presentation.authentication.EmailValidationScreen
import com.example.healthhub.presentation.authentication.IdValidationScreen
import com.example.healthhub.presentation.authentication.viewmodel.AuthenticationViewModel
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationUiState
import com.example.healthhub.presentation.theme.HealthHubTheme

class AuthenticationActivity : ComponentActivity() {
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthHubTheme {
                when (viewModel.uiState.authenticationStep) {
                    AuthenticationUiState.Step.AUTHENTICATION -> AuthenticationScreen(
                        modifier = Modifier.fillMaxSize(),
                        onAuthenticationClick = viewModel::authenticate
                    )

                    AuthenticationUiState.Step.EMAIL_VALIDATION -> EmailValidationScreen(
                        modifier = Modifier.fillMaxSize(),
                        onNextStepClick = viewModel::getAccountValidationStatus
                    )

                    AuthenticationUiState.Step.ID_VALIDATION -> IdValidationScreen(
                        modifier = Modifier.fillMaxSize(),
                        onIdImageUriReady = viewModel::uploadImage
                    )

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
