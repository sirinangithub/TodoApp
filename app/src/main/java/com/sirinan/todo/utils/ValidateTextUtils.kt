package com.sirinan.todo.utils

import android.util.Patterns

val String.isPhoneNumber get() =  length in 9..10 && all { it.isDigit() }
val String.isEmail get() =  Patterns.EMAIL_ADDRESS.matcher(this).matches()
val String.isPasswordLength get() =  this.length >= 8