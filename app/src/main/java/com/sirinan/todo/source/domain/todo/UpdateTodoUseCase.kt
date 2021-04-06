package com.sirinan.todo.source.domain.todo

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.todo.TodoStatusItem
import com.sirinan.todo.source.model.todo.TodoStatusModel
import com.sirinan.todo.source.model.todo.UpdateTodoBody
import com.sirinan.todo.source.model.todo.toItem
import com.sirinan.todo.source.remote.repository.TodoRepository

class UpdateTodoUseCase(private val repository: TodoRepository) {

    suspend fun execute(
        id: String,
        body: UpdateTodoBody,
        token: String
    ): CallBackResponse<TodoStatusItem> {
        val response = repository.update(id, body, token)
        return response.makeCallback {
            it.toItem()
        }
    }
}