package com.example.healthhub.ui.navigation.util

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.example.healthhub.ui.navigation.model.NavDestination

val NavBackStackEntry.navDestination: NavDestination?
    get() = when (this.destination.route) {
        NavDestination.Home.asRoute() -> this.toRoute<NavDestination.Home>()
        NavDestination.MedicalFile.asRoute() -> this.toRoute<NavDestination.MedicalFile>()
        NavDestination.Appointments.asRoute() -> this.toRoute<NavDestination.Appointments>()
        NavDestination.Info.asRoute() -> this.toRoute<NavDestination.Info>()
        NavDestination.Account.asRoute() -> this.toRoute<NavDestination.Account>()
        NavDestination.Locations.asRoute() -> this.toRoute<NavDestination.Locations>()
        NavDestination.CreateAppointment().asRoute() -> this.toRoute<NavDestination.CreateAppointment>()
        NavDestination.Medics.asRoute() -> this.toRoute<NavDestination.Medics>()
        else -> null
    }
