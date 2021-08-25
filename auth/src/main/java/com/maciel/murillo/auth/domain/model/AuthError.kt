package com.maciel.murillo.auth.domain.model

sealed class AuthError(val message: String) {

    data class Signup(val msg: String) : AuthError(msg)

    data class Login(val msg: String) : AuthError(msg)

    data class Logout(val msg: String) : AuthError(msg)
}
