package com.alex.jwtauthclient.ui

data class AuthState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = ""
)