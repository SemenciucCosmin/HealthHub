package com.example.healthhub.data.util

import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

internal fun Color.isBright(): Boolean {
    val red = (this.red * 255).roundToInt()
    val green = (this.green * 255).roundToInt()
    val blue = (this.blue * 255).roundToInt()
    val luminance = (0.299 * red + 0.587 * green + 0.114 * blue)
    return luminance > 186
}

val Color.overlayColor: Color
    get() = if (this.isBright()) Color.Black else Color.White
