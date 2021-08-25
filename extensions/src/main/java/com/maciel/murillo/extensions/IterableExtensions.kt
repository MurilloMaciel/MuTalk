package com.maciel.murillo.extensions

inline fun <T> Iterable<T>.forEachNotNull(action: (T) -> Unit): Unit {
    for (element in this) element?.let { action(it) }
}