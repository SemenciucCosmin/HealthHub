package com.example.healthhub.ui.navigation.util

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    throw IllegalStateException("No NavController provided")
}
