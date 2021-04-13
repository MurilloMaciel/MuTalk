package com.example.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.Scope

inline fun <reified T : ViewModel> Scope.sharedViewModel(
        lifecycleOwner: LifecycleOwner?,
        noinline parameters: ParametersDefinition? = null
) = lifecycleOwner?.run { getViewModel<T>(owner = lifecycleOwner, parameters = parameters) }
