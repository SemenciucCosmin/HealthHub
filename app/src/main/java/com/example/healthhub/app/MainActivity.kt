package com.example.healthhub.app

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthhub.ui.catalog.components.TopAppBar
import com.example.healthhub.ui.catalog.theme.HealthHubTheme
import com.example.healthhub.data.account.model.User
import com.example.healthhub.navigation.NavigationGraph
import com.example.healthhub.ui.navigation.components.BottomNavigationBar
import com.example.healthhub.ui.navigation.model.NavDestination
import com.example.healthhub.ui.navigation.model.bottomNavigationItems
import com.example.healthhub.ui.navigation.util.LocalNavController
import com.example.healthhub.ui.navigation.util.navDestination
import com.example.healthhub.data.util.BLANK
import com.example.healthhub.domain.account.GetUserUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getUserUseCase: GetUserUseCase by inject()
    private var user by mutableStateOf<User?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.lifecycleScope.launch {
            getUserUseCase().collectLatest { user = it }
        }

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.navDestination ?: NavDestination.Home
            val bottomNavigationDestinations = bottomNavigationItems.map { it.destination }
            val isMainDestination = currentDestination in bottomNavigationDestinations

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
