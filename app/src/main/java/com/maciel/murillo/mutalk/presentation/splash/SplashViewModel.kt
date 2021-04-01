package com.maciel.murillo.mutalk.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.mutalk.core.helper.Event
import com.maciel.murillo.mutalk.domain.usecase.auth.IsUserLoggedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_TIME = 2500L

class SplashViewModel(
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Event<Unit>>()
    val navigateToLogin: LiveData<Event<Unit>> = _navigateToLogin

    private val _navigateToMain = MutableLiveData<Event<Unit>>()
    val navigateToMain: LiveData<Event<Unit>> = _navigateToMain

    private fun navigateToInitialPage() {
        if (isUserLoggedUseCase()) {
            _navigateToMain.postValue(Event(Unit))
        } else {
            _navigateToLogin.postValue(Event(Unit))
        }
    }

    fun onInit() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(SPLASH_TIME)
            navigateToInitialPage()
        }
    }
}