package com.example.healthhub.presentation.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthhub.data.di.UserScope
import com.example.healthhub.data.util.BLANK
import com.example.healthhub.navigation.BottomNavigationBar
import com.example.healthhub.navigation.LocalNavController
import com.example.healthhub.navigation.NavDestination
import com.example.healthhub.navigation.NavigationGraph
import com.example.healthhub.navigation.bottomNavigationItems
import com.example.healthhub.navigation.navDestination
import com.example.healthhub.presentation.theme.HealthHubTheme
import com.example.healthhub.presentation.ui.TopAppBar
import org.koin.compose.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.navDestination ?: NavDestination.Home
            val bottomNavigationDestinations = bottomNavigationItems.map { it.destination }
            val isMainDestination = currentDestination in bottomNavigationDestinations
            val user = UserScope.getUser(getKoin())

            HealthHubTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                userNameInitials = user?.getNameInitials() ?: String.BLANK,
                                profileEnabled = isMainDestination,
                                navigationEnabled = !isMainDestination,
                                onNavigationClick = navController::navigateUp,
                                onProfileClick = { navController.navigate(NavDestination.Account) }
                            )
                        },
                        bottomBar = {
                            AnimatedVisibility(
                                visible = isMainDestination,
                                enter = expandVertically(),
                                exit = shrinkVertically(),
                                content = { BottomNavigationBar() }
                            )
                        }
                    ) { paddingValues ->
                        NavigationGraph(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun startActivity(activity: AuthenticationActivity) {
            val intent = Intent(activity, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }

            activity.startActivity(intent)
            activity.finish()
        }
    }
}
