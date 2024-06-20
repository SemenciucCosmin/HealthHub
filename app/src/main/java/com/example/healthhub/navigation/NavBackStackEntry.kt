package com.example.healthhub.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute

val NavBackStackEntry.navDestination: NavDestination?
    get() = when (this.destination.route) {
        NavDestination.Register.asRoute() -> this.toRoute<NavDestination.Register>()
        NavDestination.Login.asRoute() -> this.toRoute<NavDestination.Login>()
        NavDestination.Home.asRoute() -> this.toRoute<NavDestination.Home>()
        NavDestination.Appointments.asRoute() -> this.toRoute<NavDestination.Appointments>()
        NavDestination.MedicalFile.asRoute() -> this.toRoute<NavDestination.MedicalFile>()
        NavDestination.Info.asRoute() -> this.toRoute<NavDestination.Info>()
        else -> null
    }
