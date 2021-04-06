package com.sirinan.todo.source.model.todo


import com.google.gson.annotations.SerializedName

data class TodoCountModel(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("data")
    var data: MutableList<TodoModel> = mutableListOf()
)