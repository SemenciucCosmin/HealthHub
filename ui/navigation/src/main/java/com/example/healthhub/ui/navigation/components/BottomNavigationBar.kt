package com.example.healthhub.ui.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.model.bottomNavigationItems
import com.example.healthhub.ui.navigation.util.LocalNavController
import com.example.healthhub.ui.navigation.util.navDestination

@Composable
fun BottomNavigationBar(onItemClick: (NavDestination) -> Unit) {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        bottomNavigationItems.forEach { navigationItem ->
            NavigationBarItem(
                selected = navBackStackEntry?.navDestination == navigationItem.destination,
                onClick = { onItemClick(navigationItem.destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = navigationItem.icon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(text = stringResource(id = navigationItem.label))
                }
            )
        }
    }
}
