package com.sirinan.todo.core

import android.os.Bundle

sealed class CallBackResponse<out R> {
    data class Success<T>(val data: T) : CallBackResponse<T>()
    data class Error(val message: String) : CallBackResponse<Nothing>()

    fun callback(): CallBackResponse<R> {
        return when (this) {
            is Success -> {
                Success(this.data)
            }
            is Error -> {
                Error(this.message)
            }
        }
    }
    inline fun <T> makeCallback(blockSuccess: (R) -> T):  CallBackResponse<T> {
        return when (this) {
            is Success -> {
                Success( blockSuccess.invoke(this.data))
            }
            is Error -> {
                Error(this.message)
            }
        }
    }

    inline fun callback(blockSuccess: (R) -> Unit, blockError: (String) -> Unit) {
        return when (this) {
            is Success -> {
                blockSuccess.invoke(this.data)
            }
            is Error -> {
                blockError.invoke(this.message)
            }
        }
    }
}


