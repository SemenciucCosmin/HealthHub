package com.example.healthhub.presentation.authentication

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.R
import com.example.healthhub.data.util.BLANK
import com.example.healthhub.presentation.theme.HealthHubTheme

@Composable
fun AuthenticationScreen(
    onAuthenticationClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf(String.BLANK) }
    var password by remember { mutableStateOf(String.BLANK) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.lbl_welcome),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = email,
                isError = isEmailError,
                singleLine = true,
                onValueChange = {
                    email = it
                    isEmailError = email.isBlank()
                },
                placeholder = {
                    Text(text = stringResource(R.string.lbl_email_placeholder))
                },
                supportingText = {
                    if (isEmailError) {
                        Text(text = stringResource(R.string.lbl_email_blank_error))
                    }
                },
            )

            OutlinedTextField(
                value = password,
                isError = isPasswordError,
                singleLine = true,
                onValueChange = {
                    password = it
                    isPasswordError = password.isBlank()
                },
                placeholder = {
                    Text(text = stringResource(R.string.lbl_password_placeholder))
                },
                supportingText = {
                    if (isPasswordError) {
                        Text(text = stringResource(R.string.lbl_password_blank_error))
                    }
                },
            )

            Button(
                shape = MaterialTheme.shapes.small,
                onClick = {
                    when {
                        email.isNotBlank() && password.isNotBlank() -> {
                            onAuthenticationClick(email, password)
                        }

                        else -> {
                            isEmailError = email.isBlank()
                            isPasswordError = password.isBlank()
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.lbl_authentication_action),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AuthenticationContentPreview() {
    HealthHubTheme {
        AuthenticationScreen(
            onAuthenticationClick = { _, _ -> },
            modifier = Modifier.fillMaxSize()
        )
    }
}