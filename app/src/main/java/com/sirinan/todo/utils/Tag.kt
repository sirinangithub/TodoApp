package com.sirinan.todo.utils

import android.util.Log
import com.sirinan.todo.BuildConfig
import kotlin.reflect.KClass

fun <T : Any> T.logDebug(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}

fun <T : Any> T.logError(message: String?) {
    if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, "unknow error")
}

val <T : Any> T.kClass: KClass<T> get() = javaClass.kotlin

