package com.example.healthhub.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    throw IllegalStateException("No NavController provided")
}
