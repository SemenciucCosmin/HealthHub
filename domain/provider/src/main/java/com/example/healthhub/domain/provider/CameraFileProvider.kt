package com.example.healthhub.domain.provider

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.healthhub.ui.catalog.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Camera file provider.
 */
class CameraFileProvider : FileProvider(R.xml.filepaths) {
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

        fun getFileFromUri(context: Context, uri: Uri): File? {
            val contentResolver = context.contentResolver
            val file = File(context.cacheDir, FILE_PREFIX + FILE_EXTENSION)

            try {
                val inputStream: InputStream? = contentResolver.openInputStream(uri)
                val outputStream: OutputStream = FileOutputStream(file)

                inputStream?.use { input ->
                    outputStream.use { output ->
                        val buffer = ByteArray(4 * 1024) // Buffer size
                        var byteCount: Int
                        while (input.read(buffer).also { byteCount = it } != -1) {
                            output.write(buffer, 0, byteCount)
                        }
                        output.flush()
                    }
                }
                return file
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }
    }
}