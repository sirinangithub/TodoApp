package com.sirinan.todo.core

import retrofit2.Response

fun <T> Response<T>.callback() =
    if (this.isSuccessful) {
        val response = this.body()
        if (response != null)
            CallBackResponse.Success(this.body()!!)
        else
            CallBackResponse.Error("Data not fond")
    } else {
        CallBackResponse.Error(this.message())
    }