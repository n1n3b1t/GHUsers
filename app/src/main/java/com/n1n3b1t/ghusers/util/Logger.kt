package com.n1n3b1t.ghusers.util

import android.util.Log
import com.n1n3b1t.ghusers.BuildConfig

/**
 * Created by valentintaranenko on 27/12/2017.
 */
fun d(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun i(tag: String, message: String) {
    Log.i(tag, message)
}

fun e(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, message)
    }
}


fun Any.d(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(this.javaClass.simpleName, message)
    }
}

fun Any.i(message: String) {
    Log.i(this.javaClass.simpleName, message)
}

fun Any.e(message: String, exception: Throwable? = null) {
    if (BuildConfig.DEBUG) {
        Log.e(this.javaClass.simpleName, message, exception)
    }
}

inline fun <A, B, R> ifNotNull(a: A?, b: B?, code: (A, B) -> R) {
    if (a != null && b != null) {
        code(a, b)
    }
}