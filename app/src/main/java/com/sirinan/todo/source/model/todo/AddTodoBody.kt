package com.sirinan.todo.source.model.todo


import com.google.gson.annotations.SerializedName

data class AddTodoBody(
    @SerializedName("description")
    var description: String = ""
)