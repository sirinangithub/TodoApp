package com.sirinan.todo.source.model.todo


import com.google.gson.annotations.SerializedName

data class UpdateTodoBody(
    @SerializedName("completed")
    var completed: Boolean = false,
    @SerializedName("description")
var description: String = ""
)