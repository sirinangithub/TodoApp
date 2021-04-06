package com.sirinan.todo.utils

import android.content.Context
import com.sirinan.todo.source.domain.useCaseModule
import com.sirinan.todo.source.remote.serviceModule
import com.sirinan.todo.ui.register.registerMode
import com.sirinan.todo.ui.todo.todoMode
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInjectionUtils(context: Context) {

    init {
        startKoin {
            androidContext(context)
            modules(
                arrayListOf(
                    useCaseModule,
                    serviceModule,
                    registerMode,
                    todoMode
                )
            )
        }
    }
}
