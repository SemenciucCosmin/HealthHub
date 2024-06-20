package com.example.healthhub.navigation

import com.example.healthhub.R

data class BottomNavigationItem(
    val label: Int,
    val icon: Int,
    val destination: NavDestination,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = R.string.lbl_home,
        icon = R.drawable.ic_mock,
        destination = NavDestination.Home
    ),
    BottomNavigationItem(
        label = R.string.lbl_appointments,
        icon = R.drawable.ic_mock,
        destination = NavDestination.Appointments
    ),
    BottomNavigationItem(
        label = R.string.lbl_medical_file,
        icon = R.drawable.ic_mock,
        destination = NavDestination.MedicalFile
    ),
    BottomNavigationItem(
        label = R.string.lbl_info,
        icon = R.drawable.ic_mock,
        destination = NavDestination.Info
    )
)
