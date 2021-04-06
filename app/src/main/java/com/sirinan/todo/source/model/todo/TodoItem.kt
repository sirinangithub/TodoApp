package com.sirinan.todo.source.model.todo

import android.os.Parcelable
import com.sirinan.todo.utils.DateTimeUtils
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoItem(
    var completed: Boolean = false,
    var id: String = "",
    var description: String = "",
    var owner: String = "",
    var updatedDate: String = "",
    var updatedTime: String = ""
):Parcelable

fun MutableList<TodoModel>.toItem(): MutableList<TodoItem> {
    return map {
        it.toItem()
    }.sortedByDescending { it.id }.toMutableList()
}
fun TodoModel.toItem(): TodoItem {
    return TodoItem(completed, id, description, owner, DateTimeUtils.toDate(updatedAt),DateTimeUtils.toTime(updatedAt))
}