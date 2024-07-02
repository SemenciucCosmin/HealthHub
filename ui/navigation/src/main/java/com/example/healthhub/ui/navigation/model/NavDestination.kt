package com.example.healthhub.ui.navigation.model

import androidx.annotation.StringRes
import com.example.healthhub.ui.catalog.R
import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestination(@StringRes val stringRes: Int) {

    @Serializable
    data object Home : NavDestination(R.string.lbl_home)

    @Serializable
    data object MedicalFile : NavDestination(R.string.lbl_medical_file)

    @Serializable
    data object Appointments : NavDestination(R.string.lbl_appointments)

    @Serializable
    data object Info : NavDestination(R.string.lbl_info)

    @Serializable
    data object Account : NavDestination(R.string.lbl_account)

    @Serializable
    data object Locations : NavDestination(R.string.lbl_locations)

    @Serializable
    data object CreateAppointment : NavDestination(R.string.lbl_create_appointment)

    @Serializable
    data object Medics : NavDestination(R.string.lbl_medics)

    fun asRoute(): String? = this.javaClass.canonicalName
}
