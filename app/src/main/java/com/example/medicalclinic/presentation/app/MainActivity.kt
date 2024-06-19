package com.example.medicalclinic.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medicalclinic.navigation.BottomNavigationBar
import com.example.medicalclinic.navigation.LocalNavController
import com.example.medicalclinic.navigation.NavDestination
import com.example.medicalclinic.navigation.NavigationGraph
import com.example.medicalclinic.presentation.theme.MedicalClinicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val shouldShowBottomBar = navBackStackEntry?.destination?.route !in listOf(
                NavDestination.Login.asRoute(),
                NavDestination.Register.asRoute()
            )

            MedicalClinicTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = { if (shouldShowBottomBar) BottomNavigationBar() }
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
