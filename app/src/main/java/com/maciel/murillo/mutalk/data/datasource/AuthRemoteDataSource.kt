package com.maciel.murillo.mutalk.data.datasource

import com.google.firebase.auth.FirebaseUser

interface AuthRemoteDataSource {

    fun isUserLogged(): Boolean

    suspend fun signup(email: String, password: String): FirebaseUser?

    suspend fun login(email: String, password: String): FirebaseUser?

    suspend fun logout()
}