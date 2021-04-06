package com.sirinan.todo.core


abstract class BaseUseCase<in P, R> {

    abstract suspend fun execute(params: P): CallBackResponse<R>
}