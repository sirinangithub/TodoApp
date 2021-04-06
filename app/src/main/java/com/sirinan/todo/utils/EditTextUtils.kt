package com.sirinan.todo.utils

import com.google.android.material.textfield.TextInputEditText

val TextInputEditText.textOrEmpty get() = this.text.toString()?:""
val TextInputEditText.intDefault get() = this.text.toString().toIntOrNull()?:0