package com.example.healthhub.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute

val NavBackStackEntry.navDestination: NavDestination?
    get() = when (this.destination.route) {
        NavDestination.Authentication.asRoute() -> this.toRoute<NavDestination.Authentication>()
        NavDestination.Home.asRoute() -> this.toRoute<NavDestination.Home>()
        NavDestination.Appointments.asRoute() -> this.toRoute<NavDestination.Appointments>()
        NavDestination.MedicalFile.asRoute() -> this.toRoute<NavDestination.MedicalFile>()
        NavDestination.Info.asRoute() -> this.toRoute<NavDestination.Info>()
        else -> null
    }
