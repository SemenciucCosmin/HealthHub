package com.example.healthhub.domain.provider

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.healthhub.R
import java.io.File

/**
 * Camera file provider.
 */
internal class CameraFileProvider : FileProvider(R.xml.filepaths) {
    companion object {
        private const val FILE_PREFIX = "selected_image_"
        private const val FILE_EXTENSION = ".png"
        private const val CACHE_SUBDIRECTORY = "images"
        private const val FILE_PROVIDER_AUTHORITY = "com.example.healthhub.camera.provider"

        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, CACHE_SUBDIRECTORY).apply { mkdirs() }
            val file = File.createTempFile(FILE_PREFIX, FILE_EXTENSION, directory)
            return getUriForFile(context, FILE_PROVIDER_AUTHORITY, file)
        }
    }
}