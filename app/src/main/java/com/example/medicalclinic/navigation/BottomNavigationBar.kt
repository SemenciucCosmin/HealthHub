package com.example.medicalclinic.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute

@Composable
fun BottomNavigationBar() {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = when (navBackStackEntry?.destination?.route) {
        NavDestination.Home.asRoute() -> {
            navBackStackEntry?.toRoute<NavDestination.Home>()
        }

        NavDestination.Appointments.asRoute() -> {
            navBackStackEntry?.toRoute<NavDestination.Appointments>()
        }

        NavDestination.MedicalFile.asRoute() -> {
            navBackStackEntry?.toRoute<NavDestination.MedicalFile>()
        }

        NavDestination.Info.asRoute() -> {
            navBackStackEntry?.toRoute<NavDestination.Info>()
        }

        else -> null
    }

    NavigationBar {
        bottomNavigationItems.forEach { navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.destination,
                onClick = {
                    navController.navigate(navigationItem.destination) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navigationItem.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = navigationItem.label))
                }
            )
        }
    }
}
