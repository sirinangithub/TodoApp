package com.sirinan.todo.source.model.todo


import com.google.gson.annotations.SerializedName

data class TodoModel(
    @SerializedName("completed")
    var completed: Boolean = false,
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("owner")
    var owner: String = "",
    @SerializedName("createdAt")
    var createdAt: String = "",
    @SerializedName("updatedAt")
    var updatedAt: String = "",
    @SerializedName("__v")
    var v: Int = 0
)