package com.maciel.murillo.mutalk.core.extensions

import android.content.Context
import android.widget.Toast

fun Int.showToast(context: Context?, duration: Int = Toast.LENGTH_SHORT) {
    context?.let {
        Toast.makeText(context, this, duration).show()
    }
}

fun String.showToast(context: Context?, duration: Int = Toast.LENGTH_SHORT) {
    context?.let {
        Toast.makeText(context, this, duration).show()
    }
}