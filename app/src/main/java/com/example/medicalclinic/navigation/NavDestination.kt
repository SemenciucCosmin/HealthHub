package com.example.medicalclinic.navigation

import kotlinx.serialization.Serializable
import org.koin.core.component.getScopeName

sealed class NavDestination {
    @Serializable
    data object Register : NavDestination()

    @Serializable
    data object Login : NavDestination()

    @Serializable
    data object Home : NavDestination()

    @Serializable
    data object Appointments : NavDestination()

    @Serializable
    data object MedicalFile : NavDestination()

    @Serializable
    data object Info : NavDestination()

    fun asRoute() = this.getScopeName().toString().replace("$", ".")
}