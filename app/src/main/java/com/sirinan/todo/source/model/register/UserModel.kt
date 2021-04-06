package com.sirinan.todo.source.model.register


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("age")
    var age: Int = 0,
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("createdAt")
    var createdAt: String = "",
    @SerializedName("updatedAt")
    var updatedAt: String = "",
    @SerializedName("__v")
    var v: Int = 0
)