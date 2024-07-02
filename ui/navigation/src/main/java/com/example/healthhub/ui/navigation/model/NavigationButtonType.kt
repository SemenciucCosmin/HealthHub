package com.example.healthhub.ui.navigation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.healthhub.ui.catalog.R

sealed class NavigationButtonType(
    @DrawableRes val drawableRes: Int,
    val color: Color,
    val destination: NavDestination,
) {
    data object Account : NavigationButtonType(
        drawableRes = R.drawable.ic_parent_profile,
        color = Color(0xFF66A2D4),
        destination = NavDestination.Account
    )

    data object Location : NavigationButtonType(
        drawableRes = R.drawable.ic_maps,
        color = Color(0xFF7DB590),
        destination = NavDestination.Locations
    )

    data object Medics: NavigationButtonType(
        drawableRes = R.drawable.ic_medic,
        color = Color(0xFF7B2860),
        destination = NavDestination.Medics
    )

    data object FutureAppointments: NavigationButtonType(
        drawableRes = R.drawable.ic_appointments,
        color = Color(0xFFEEBA4A),
        destination = NavDestination.Appointments
    )
}