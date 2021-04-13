package com.example.core.helper

import android.content.ComponentCallbacks
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

fun getOrCreateScope(
    componentCallback: ComponentCallbacks,
    scopeId: String,
    name: String,
    loadDIModules: (() -> Unit)
): Scope {
    return try {
        componentCallback.getKoin().getOrCreateScope(scopeId, named(name))
    } catch (e: Exception) {
        loadDIModules()
        componentCallback.getKoin().getOrCreateScope(scopeId, named(name))
    }
}

fun closeScope(componentCallback: ComponentCallbacks, scopeId: String) {
    componentCallback.getKoin().getScopeOrNull(scopeId)?.close()
}
