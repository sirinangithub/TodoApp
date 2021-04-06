package com.sirinan.todo.source.domain

import com.sirinan.todo.source.domain.todo.*
import com.sirinan.todo.source.domain.user.LoginUseCase
import com.sirinan.todo.source.domain.user.RegisterUseCase
import org.koin.dsl.module

val useCaseModule= module {

    factory { RegisterUseCase(get()) }
    factory { LoginUseCase(get()) }

    factory { AddTodoUseCase(get()) }
    factory { GetAllTodoUseCase(get()) }
    factory { GetTodoByIdUseCase(get()) }
    factory { DeleteTodoUseCase(get()) }
    factory { UpdateTodoUseCase(get()) }
}