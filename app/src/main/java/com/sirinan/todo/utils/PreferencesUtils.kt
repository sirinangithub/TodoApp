package com.sirinan.todo.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesUtils {

    private lateinit var preferences: SharedPreferences
    private lateinit var preferencesSetting: SharedPreferences

    companion object{
        val instant: PreferencesUtils by lazy { PreferencesUtils() }
    }

    fun initialize(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
    var token: String
        get() = preferences.getString("token", "") ?: ""
        set(value) = preferences.edit {
            putString("token", value)
        }

    fun logout() {
        preferences.edit { remove("token") }
    }
}