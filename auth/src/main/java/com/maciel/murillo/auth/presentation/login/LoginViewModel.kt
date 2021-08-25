package com.maciel.murillo.auth.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.extensions.isEmailValid
import com.maciel.murillo.util.event.Event
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.maciel.murillo.auth.domain.usecase.LoginUseCase
import com.maciel.murillo.auth.presentation.model.AuthPresentationError
import com.maciel.murillo.util.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private var email = ""
    private var password = ""

    // todo: rafactor to accept a throwable into AuthError and remove this
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is FirebaseAuthInvalidCredentialsException -> postLoginError(AuthPresentationError.WRONG_USER_INFO)
            is FirebaseAuthInvalidUserException -> postLoginError(AuthPresentationError.USER_NOT_EXISTS)
            else -> postLoginError(AuthPresentationError.GENERIC)
        }
    }

    private val _loginError = MutableLiveData<Event<AuthPresentationError>>()
    val loginError: LiveData<Event<AuthPresentationError>> = _loginError

    private val _loginSuccessfull = MutableLiveData<Event<Unit>>()
    val loginSuccessfull: LiveData<Event<Unit>> = _loginSuccessfull

    private val _signup = MutableLiveData<Event<Unit>>()
    val signup: LiveData<Event<Unit>> = _signup

    private fun postLoginError(authError: AuthPresentationError): Boolean {
        _loginError.postValue(Event(authError))
        return false
    }

    private fun isFormValid(): Boolean {
        return when {
            email.isEmpty() -> postLoginError(AuthPresentationError.EMPTY_EMAIL)
            password.isEmpty() -> postLoginError(AuthPresentationError.EMPTY_PASSWORD)
            email.isEmailValid().not() -> postLoginError(AuthPresentationError.EMAIL_INVALID)
            else -> true
        }
    }

    private fun login() {
        viewModelScope.launch(dispatcherProvider.io() + exceptionHandler) {
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