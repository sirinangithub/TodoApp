package com.sirinan.todo.core


abstract class BasePairUseCase<in P,T, R> {
    abstract suspend fun execute(first: P,second: T): CallBackResponse<R>
}