package com.alex.jwtauthclient.ui

sealed class AuthUiEvent {
    data class UsernameChanged(val value: String) : AuthUiEvent()
    data class PasswordChanged(val value: String) : AuthUiEvent()
    object SignUp : AuthUiEvent()
    object SignIn : AuthUiEvent()

}