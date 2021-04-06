package com.sirinan.todo.source.model.register


import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("age")
    var age: Int = 0
)