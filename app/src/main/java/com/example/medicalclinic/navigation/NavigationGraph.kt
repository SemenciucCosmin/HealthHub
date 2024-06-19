package com.example.medicalclinic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medicalclinic.presentation.appointments.AppointmentsRoute
import com.example.medicalclinic.presentation.home.HomeRoute
import com.example.medicalclinic.presentation.info.InfoRoute
import com.example.medicalclinic.presentation.login.LoginRoute
import com.example.medicalclinic.presentation.login.RegistrationRoute
import com.example.medicalclinic.presentation.medicalfile.MedicalFileRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Register,
        modifier = modifier
    ) {
        composable<NavDestination.Register> { RegistrationRoute() }
        composable<NavDestination.Login> { LoginRoute() }
        composable<NavDestination.Home> { HomeRoute() }
        composable<NavDestination.Appointments> { AppointmentsRoute() }
        composable<NavDestination.MedicalFile> { MedicalFileRoute() }
        composable<NavDestination.Info> { InfoRoute() }
    }
}
