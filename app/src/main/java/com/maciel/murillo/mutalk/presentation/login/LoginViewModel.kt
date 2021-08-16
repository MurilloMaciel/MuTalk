package com.maciel.murillo.mutalk.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.util.extensions.isEmailValid
import com.maciel.murillo.util.event.Event
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.maciel.murillo.mutalk.domain.usecase.auth.LoginUseCase
import com.maciel.murillo.mutalk.presentation.model.AuthError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private var email = ""
    private var password = ""

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is FirebaseAuthInvalidCredentialsException -> postLoginError(AuthError.WRONG_USER_INFO)
            is FirebaseAuthInvalidUserException -> postLoginError(AuthError.USER_NOT_EXISTS)
            else -> postLoginError(AuthError.GENERIC)
        }
    }

    private val _loginError = MutableLiveData<Event<AuthError>>()
    val loginError: LiveData<Event<AuthError>> = _loginError

    private val _loginSuccessfull = MutableLiveData<Event<Unit>>()
    val loginSuccessfull: LiveData<Event<Unit>> = _loginSuccessfull

    private val _signup = MutableLiveData<Event<Unit>>()
    val signup: LiveData<Event<Unit>> = _signup

    private fun postLoginError(authError: AuthError): Boolean {
        _loginError.postValue(Event(authError))
        return false
    }

    private fun isFormValid(): Boolean {
        return when {
            email.isEmpty() -> postLoginError(AuthError.EMPTY_EMAIL)
            password.isEmpty() -> postLoginError(AuthError.EMPTY_PASSWORD)
            email.isEmailValid().not() -> postLoginError(AuthError.EMAIL_INVALID)
            else -> true
        }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            loginUseCase(email, password)
            _loginSuccessfull.postValue(Event(Unit))
        }
    }

    fun onClickLogin() {
        if (isFormValid()) {
            login()
        }
    }

    fun onClickSignup() {
        _signup.postValue(Event(Unit))
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }
}