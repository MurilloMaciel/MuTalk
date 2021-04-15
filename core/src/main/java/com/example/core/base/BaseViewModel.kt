package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.helper.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val _connectivityError = MutableLiveData<Event<Unit>>()
    val connectivityError: LiveData<Event<Unit>> = _connectivityError

    protected val connectivityExceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        if (throwable is NoConnectivityException) {
//            _connectivityError.postValue(Event(Unit))
//        }
    }

    private fun getCoroutineContext(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        exceptionHandler: CoroutineExceptionHandler? = null
    ): CoroutineContext {
        return (dispatcher + connectivityExceptionHandler).apply {
            if (exceptionHandler != null) {
                this + exceptionHandler
            }
        }
    }

    protected fun runSuspend(
        block: () -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        exceptionHandler: CoroutineExceptionHandler? = null
    ) {
        viewModelScope.launch(getCoroutineContext(dispatcher, exceptionHandler)) {
            block()
        }
    }
}