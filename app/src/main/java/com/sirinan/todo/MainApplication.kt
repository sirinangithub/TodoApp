package com.sirinan.todo

import android.app.Application
import android.content.Context
import com.sirinan.todo.utils.KoinInjectionUtils
import com.sirinan.todo.utils.PreferencesUtils

class MainApplication : Application() {

    init {
        instance = this
    }
    companion object {
        private var instance: MainApplication? = null
        val context : Context get() = instance!!.applicationContext
    }


    override fun onCreate() {
        super.onCreate()
        KoinInjectionUtils(applicationContext)
        PreferencesUtils.instant.initialize(applicationContext)
    }
}