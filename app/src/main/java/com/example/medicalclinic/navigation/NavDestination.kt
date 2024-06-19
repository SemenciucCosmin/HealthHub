package com.example.medicalclinic.navigation

import kotlinx.serialization.Serializable
import org.koin.core.component.getScopeName

@Serializable
sealed class NavDestination(
    val route: String = this.getScopeName().toString().replace("$", ".")
) {
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