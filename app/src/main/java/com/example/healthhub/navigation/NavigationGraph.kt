package com.example.healthhub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthhub.app.AuthenticationActivity
import com.example.healthhub.app.MainActivity
import com.example.healthhub.feature.account.AccountRoute
import com.example.healthhub.feature.appointments.AppointmentsRoute
import com.example.healthhub.feature.home.HomeRoute
import com.example.healthhub.feature.info.InfoRoute
import com.example.healthhub.feature.info.LocationsRoute
import com.example.healthhub.feature.medicalfile.MedicalFileRoute
import com.example.healthhub.ui.navigation.model.NavDestination

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val mainActivity = LocalContext.current as MainActivity
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home,
        modifier = modifier
    ) {
        composable<NavDestination.Home> { HomeRoute() }
        composable<NavDestination.MedicalFile> { MedicalFileRoute() }
        composable<NavDestination.Appointments> { AppointmentsRoute() }
        composable<NavDestination.Info> { InfoRoute() }
        composable<NavDestination.Account> {
            AccountRoute(
                onSignOut = {
                    AuthenticationActivity.startActivity(mainActivity)
                    mainActivity.finish()
                }
            )
        }
        composable<NavDestination.Locations> { LocationsRoute() }
    }
}
