package com.maciel.murillo.mutalk.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.extensions.isEmailValid
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.example.core.helper.Event
import com.maciel.murillo.mutalk.domain.usecase.auth.SignupUseCase
import com.maciel.murillo.mutalk.presentation.model.AuthError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUseCase: SignupUseCase,
) : ViewModel() {

    private var name = ""
    private var email = ""
    private var password = ""
    private var passwordConfirm = ""

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is FirebaseAuthWeakPasswordException -> postSignupError(AuthError.STRONGEST_PASSWORD)
            is FirebaseAuthInvalidCredentialsException -> postSignupError(AuthError.VALID_EMAIL)
            is FirebaseAuthUserCollisionException -> postSignupError(AuthError.ACCOUNT_ALREADY_EXISTS)
            else -> postSignupError(AuthError.GENERIC)
        }
    }

    private val _signupError = MutableLiveData<Event<AuthError>>()
    val signupError: LiveData<Event<AuthError>> = _signupError

    private val _signupSuccessfull = MutableLiveData<Event<Unit>>()
    val signupSuccessfull: LiveData<Event<Unit>> = _signupSuccessfull

    private fun postSignupError(authError: AuthError): Boolean {
        _signupError.postValue(Event(authError))
        return false
    }

    private fun isFormValid(): Boolean {
        return when {
            name.isEmpty() -> postSignupError(AuthError.EMPTY_NAME)
            email.isEmpty() -> postSignupError(AuthError.EMPTY_EMAIL)
            password.isEmpty() -> postSignupError(AuthError.EMPTY_PASSWORD)
            email.isEmailValid().not() -> postSignupError(AuthError.EMAIL_INVALID)
            password != passwordConfirm -> postSignupError(AuthError.PASSWORD_CONFIRM)
            else -> true
        }
    }

    private fun signup() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            signupUseCase(email, password)
            _signupSuccessfull.postValue(Event(Unit))
        }
    }

    fun onClickSignup() {
        if (isFormValid()) {
            signup()
        }
    }

    fun onChangeName(name: String) {
        this.name = name
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onChangePasswordConfirm(passwordConfirm: String) {
        this.passwordConfirm = passwordConfirm
    }
}