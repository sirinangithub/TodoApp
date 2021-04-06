package com.sirinan.todo.source.model.register


import com.google.gson.annotations.SerializedName

data class UsesWithTokenModel(
    @SerializedName("user")
    var user: UserModel = UserModel(),
    @SerializedName("token")
    var token: String = ""
)