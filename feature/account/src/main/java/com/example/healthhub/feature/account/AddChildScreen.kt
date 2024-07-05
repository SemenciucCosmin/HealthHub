package com.example.healthhub.feature.account

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthhub.domain.provider.CameraFileProvider
import com.example.healthhub.feature.authentication.model.IdPhotoOption
import com.example.healthhub.ui.catalog.R
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import java.io.File

@Composable
fun AddChildScreen(
    onImageFileReady: (File) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIdPhotoOption by remember { mutableStateOf(IdPhotoOption.NONE) }

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.lbl_birth_certificate_validation_message),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Button(
                shape = MaterialTheme.shapes.small,
                onClick = { selectedIdPhotoOption = IdPhotoOption.UPLOAD }
            ) {
                Text(
                    text = stringResource(R.string.lbl_birth_certificate_upload_action),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Button(
                shape = MaterialTheme.shapes.small,
                onClick = { selectedIdPhotoOption = IdPhotoOption.CAMERA }
            ) {
                Text(
                    text = stringResource(R.string.lbl_birth_certificate_take_photo_action),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }


    when (selectedIdPhotoOption) {
        IdPhotoOption.NONE -> Unit
        IdPhotoOption.UPLOAD -> {
            val context = LocalContext.current
            val imagePicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    uri?.let {
                        val file = CameraFileProvider.getFileFromUri(context, it)
                        file?.let(onImageFileReady)
                    }
                    selectedIdPhotoOption = IdPhotoOption.NONE
                }
            )

            SideEffect {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        }

        IdPhotoOption.CAMERA -> {
            val context = LocalContext.current
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { success ->
                    if (success) {
                        imageUri?.let {
                            val file = CameraFileProvider.getFileFromUri(context, it)
                            file?.let(onImageFileReady)
                        }
                    }

                    selectedIdPhotoOption = IdPhotoOption.NONE
                }
            )

            imageUri = CameraFileProvider.getImageUri(context)
            SideEffect { imageUri?.let(cameraLauncher::launch) }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AddChildScreenPreview() {
    HealthHubTheme {
        AddChildScreen(
            onImageFileReady = {}
        )
    }
}