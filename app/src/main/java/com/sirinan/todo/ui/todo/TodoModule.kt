package com.sirinan.todo.ui.todo

import com.sirinan.todo.ui.todo.add.AddTodoViewModel
import com.sirinan.todo.ui.todo.adapter.TodoAdapter
import com.sirinan.todo.ui.todo.detail.DetailTodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val todoMode = module {
    viewModel { TodoViewModel(get(),get(),get(),get()) }
    viewModel { AddTodoViewModel(get()) }
    viewModel { DetailTodoViewModel(get()) }
    factory { TodoAdapter() }
}