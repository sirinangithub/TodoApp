package com.sirinan.todo.source.model.todo


import com.google.gson.annotations.SerializedName

data class TodoStatusModel(
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: TodoModel? = null
)