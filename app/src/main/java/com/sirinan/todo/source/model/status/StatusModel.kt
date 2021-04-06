package com.sirinan.todo.source.model.status


import com.google.gson.annotations.SerializedName

data class StatusModel(
    @SerializedName("success")
    var success: String = ""
)