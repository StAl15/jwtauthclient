package com.alex.jwtauthclient.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alex.jwtauthclient.ui.theme.JwtauthclientTheme
import com.alex.jwtauthclient.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JwtauthclientTheme {
//               DestinationsNavHost(navGraph = NavGraphs.Root)
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AuthScreen.route
                ) {
                    composable(route = Screen.AuthScreen.route) {
                        AuthScreen(navController = navController)
                    }
                    composable(route = Screen.SecretScreen.route) {
                        SecretScreen(navController = navController)
                    }
                }
            }
        }
    }
}
