package com.sirinan.todo.utils

import android.content.Context
import android.widget.Toast

fun Context.makeToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()