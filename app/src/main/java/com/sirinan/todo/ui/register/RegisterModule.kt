package com.sirinan.todo.ui.register

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val registerMode = module {
    viewModel { RegisterViewModel(get(),get()) }
}