package com.example.healthhub.ui.navigation.model

import androidx.annotation.StringRes
import com.example.healthhub.data.appointments.model.Medic
import com.example.healthhub.data.home.model.Subscription
import com.example.healthhub.data.util.BLANK
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
    data class CreateAppointment(
        val medicId: Int = Medic.INVALID_ID
    ) : NavDestination(R.string.lbl_create_appointment)

    @Serializable
    data object Medics : NavDestination(R.string.lbl_medics)

    @Serializable
    data object FilteredAppointments : NavDestination(R.string.lbl_filtered_appointments)

    @Serializable
    data object FilteredAppointmentDetails : NavDestination(R.string.lbl_filtered_appointment_details)

    @Serializable
    data object AddChild : NavDestination(R.string.lbl_filtered_appointment_details)

    @Serializable
    data class SubscriptionDetails(
        val subscriptionId: Int = Subscription.INVALID_ID,
        val specializationId: Int = Subscription.INVALID_ID,
    ) : NavDestination(R.string.lbl_subscription_details)

    fun asRoute(): String? = this.javaClass.canonicalName
}
