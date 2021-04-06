package com.sirinan.todo.source.model.todo


data class TodoStatusItem(
    var success: Boolean = false,
    var data: TodoItem? = null
)

fun TodoStatusModel.toItem(): TodoStatusItem {
    return TodoStatusItem(success, if (data != null) data!!.toItem() else null)
}