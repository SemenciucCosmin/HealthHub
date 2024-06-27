package com.example.healthhub.ui.navigation.model

import androidx.annotation.DrawableRes
import com.example.healthhub.ui.catalog.R

data class BottomNavigationItem(
    val label: Int,
    @DrawableRes val icon: Int,
    val destination: NavDestination,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = R.string.lbl_home,
        icon = R.drawable.ic_home,
        destination = NavDestination.Home
    ),
    BottomNavigationItem(
        label = R.string.lbl_medical_file,
        icon = R.drawable.ic_medical_file,
        destination = NavDestination.MedicalFile
    ),
    BottomNavigationItem(
        label = R.string.lbl_appointments,
        icon = R.drawable.ic_appointments,
        destination = NavDestination.Appointments
    ),
    BottomNavigationItem(
        label = R.string.lbl_info,
        icon = R.drawable.ic_info,
        destination = NavDestination.Info
    )
)
