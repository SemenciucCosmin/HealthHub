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
import com.example.healthhub.presentation.authentication.viewmodel.model.AuthenticationStep
import com.example.healthhub.presentation.theme.HealthHubTheme
import org.koin.androidx.compose.koinViewModel

class AuthenticationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthHubTheme {
                val viewModel = koinViewModel<AuthenticationViewModel>()
                when (viewModel.uiState.authenticationStep) {
                    AuthenticationStep.AUTHENTICATION -> AuthenticationScreen(
                        modifier = Modifier.fillMaxSize(),
                        onAuthenticationClick = { _, _ -> }
                    )

                    AuthenticationStep.EMAIL_VALIDATION -> EmailValidationScreen(
                        modifier = Modifier.fillMaxSize(),
                    )

                    AuthenticationStep.ID_VALIDATION -> IdValidationScreen(
                        modifier = Modifier.fillMaxSize(),
                        onIdImageUriReady = {}
                    )

                    AuthenticationStep.AUTHENTICATION_COMPLETED -> {
                        MainActivity.startActivity(this@AuthenticationActivity)
                        this@AuthenticationActivity.finish()
                    }
                }
            }
        }
    }
}
