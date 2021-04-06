package com.sirinan.todo.utils

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

fun Bundle?.getTitle() = this?.getString("title") ?: ""
fun Bundle?.getId() = this?.getString("id") ?: ""

fun Bundle?.stringEmpty(key: String) = this?.getString(key) ?: ""
fun Bundle?.stringNull(key: String) = this?.getString(key)
fun Bundle?.intDefault(key: String, default: Int = -1) = this?.getInt(key) ?: default
fun Bundle?.booleanDefault(key: String) = this?.getBoolean(key) ?:false
fun <T : Parcelable> Bundle?.parcelNull(key: String): T? = this?.getParcelable<T>(key)

fun Intent?.getTitle() = this?.extras?.getString("title") ?: ""
fun Intent?.getId() = this?.extras?.getString("id") ?: ""

fun Intent?.stringEmpty(key: String) = this?.extras?.getString(key) ?: ""
fun Intent?.stringNull(key: String) = this?.extras?.getString(key)
fun Intent?.intDefault(key: String, default: Int = -1) = this?.extras?.getInt(key) ?: default
fun Intent?.booleanDefault(key: String, default: Boolean = false) = this?.extras?.getBoolean(key)
        ?: default

fun <T : Parcelable> Intent?.parcelNull(key: String): T? = this?.extras?.getParcelable<T>(key)