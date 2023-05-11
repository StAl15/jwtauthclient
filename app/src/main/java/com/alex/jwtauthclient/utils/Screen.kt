package com.alex.jwtauthclient.utils

sealed class Screen(val route: String) {
    object AuthScreen: Screen("auth_screen")
    object SecretScreen: Screen("secret_screen")
}