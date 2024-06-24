package com.example.healthhub.navigation

import kotlinx.serialization.Serializable

sealed class NavDestination {
    @Serializable
    data object Home : NavDestination()

    @Serializable
    data object Appointments : NavDestination()

    @Serializable
    data object MedicalFile : NavDestination()

    @Serializable
    data object Info : NavDestination()

    @Serializable
    data object Account : NavDestination()

    fun asRoute(): String? = this.javaClass.canonicalName
}