package com.maciel.murillo.auth.data.remote

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.maciel.murillo.auth.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.auth.data.mapper.FirebaseUserToModelMapper
import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.extensions.safe
import com.maciel.murillo.util.result.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val SIGNUP_USER_NULL_ERROR = "Signup returned null FirebaseUser"
private const val LOGIN_USER_NULL_ERROR = "Login returned null FirebaseUser"

class AuthRemoteDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseUserToModelMapper: FirebaseUserToModelMapper,
) : AuthRemoteDataSource {

    override fun isUserLogged() = auth.currentUser != null

    override suspend fun logout(): Result<Unit, AuthError> = try {
        Result.Success(auth.signOut())
    } catch (e: Exception) {
        Result.Error(AuthError.Logout(e.message.safe()))
    }

    override suspend fun signup(
        email: String,
        password: String
    ): Result<UserInfo, AuthError> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().let { authResult ->
                returnResultBasedOnAuthResult(authResult, AuthError.Signup(SIGNUP_USER_NULL_ERROR))
            }
        } catch (e: Exception) {
            Result.Error(AuthError.Signup(e.message.safe()))
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<UserInfo, AuthError> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await().let { authResult ->
                returnResultBasedOnAuthResult(authResult, AuthError.Login(LOGIN_USER_NULL_ERROR))
            }
        } catch (e: Exception) {
            Result.Error(AuthError.Login(e.message.safe()))
        }
    }

    private fun returnResultBasedOnAuthResult(
        authResult: AuthResult?,
        errorToReturn: AuthError
    ) = if (authResult?.user != null) {
        Result.Success(firebaseUserToModelMapper.mapFrom(authResult.user!!))
    } else {
        Result.Error(errorToReturn)
    }
}