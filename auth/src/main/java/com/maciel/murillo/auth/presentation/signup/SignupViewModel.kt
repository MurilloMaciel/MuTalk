package com.maciel.murillo.auth.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.extensions.isEmailValid
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.maciel.murillo.auth.domain.usecase.SignupUseCase
import com.maciel.murillo.auth.presentation.model.AuthPresentationError
import com.maciel.murillo.util.event.Event
import com.maciel.murillo.util.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val signupUseCase: SignupUseCase,
) : ViewModel() {

    private var name = ""
    private var email = ""
    private var password = ""
    private var passwordConfirm = ""

    // todo: rafactor to accept a throwable into AuthError and remove this
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is FirebaseAuthWeakPasswordException -> postSignupError(AuthPresentationError.STRONGEST_PASSWORD)
            is FirebaseAuthInvalidCredentialsException -> postSignupError(AuthPresentationError.VALID_EMAIL)
            is FirebaseAuthUserCollisionException -> postSignupError(AuthPresentationError.ACCOUNT_ALREADY_EXISTS)
            else -> postSignupError(AuthPresentationError.GENERIC)
        }
    }

    private val _signupError = MutableLiveData<Event<AuthPresentationError>>()
    val signupError: LiveData<Event<AuthPresentationError>> = _signupError

    private val _signupSuccessfull = MutableLiveData<Event<Unit>>()
    val signupSuccessfull: LiveData<Event<Unit>> = _signupSuccessfull

    private fun postSignupError(authError: AuthPresentationError): Boolean {
        _signupError.postValue(Event(authError))
        return false
    }

    private fun isFormValid(): Boolean {
        return when {
            name.isEmpty() -> postSignupError(AuthPresentationError.EMPTY_NAME)
            email.isEmpty() -> postSignupError(AuthPresentationError.EMPTY_EMAIL)
            password.isEmpty() -> postSignupError(AuthPresentationError.EMPTY_PASSWORD)
            email.isEmailValid().not() -> postSignupError(AuthPresentationError.EMAIL_INVALID)
            password != passwordConfirm -> postSignupError(AuthPresentationError.PASSWORD_CONFIRM)
            else -> true
        }
    }

    private fun signup() {
        viewModelScope.launch(dispatcherProvider.io() + exceptionHandler) {
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