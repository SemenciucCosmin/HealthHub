package com.example.healthhub.presentation.app

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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthhub.navigation.BottomNavigationBar
import com.example.healthhub.navigation.LocalNavController
import com.example.healthhub.navigation.NavigationGraph
import com.example.healthhub.navigation.bottomNavigationItems
import com.example.healthhub.navigation.navDestination
import com.example.healthhub.presentation.theme.HealthHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.navDestination
            val bottomNavigationDestinations = bottomNavigationItems.map { it.destination }
            val shouldShowBottomBar = currentDestination in bottomNavigationDestinations

            HealthHubTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            AnimatedVisibility(
                                visible = shouldShowBottomBar,
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
}
