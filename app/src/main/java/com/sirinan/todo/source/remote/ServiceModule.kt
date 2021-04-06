package com.sirinan.todo.source.remote

import com.sirinan.todo.source.remote.endpoint.TodoService
import com.sirinan.todo.source.remote.endpoint.UserService
import com.sirinan.todo.source.remote.network.OkHttpBuilder
import com.sirinan.todo.source.remote.network.RetrofitBuilder
import com.sirinan.todo.source.remote.network.ServiceProperties
import com.sirinan.todo.source.remote.repository.TodoRepository
import com.sirinan.todo.source.remote.repository.TodoRepositoryImpl
import com.sirinan.todo.source.remote.repository.UserRepository
import com.sirinan.todo.source.remote.repository.UserRepositoryImpl
import org.koin.dsl.module


val serviceModule = module {

        single { OkHttpBuilder().build() }
        single { RetrofitBuilder(get()) }
        single<UserService> { get<RetrofitBuilder>().build(ServiceProperties.BASE_URL) }
        single<TodoService> { get<RetrofitBuilder>().build(ServiceProperties.BASE_URL) }

        factory<UserRepository> { UserRepositoryImpl(get()) }
        factory<TodoRepository> { TodoRepositoryImpl(get()) }
    }

