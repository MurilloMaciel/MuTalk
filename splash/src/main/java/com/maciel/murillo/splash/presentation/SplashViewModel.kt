package com.maciel.murillo.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.auth.domain.usecase.IsUserLoggedUseCase
import com.maciel.murillo.util.event.Event
import com.maciel.murillo.util.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_TIME = 2500L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
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
        viewModelScope.launch(dispatcherProvider.io()) {
            delay(SPLASH_TIME)
            navigateToInitialPage()
        }
    }
}