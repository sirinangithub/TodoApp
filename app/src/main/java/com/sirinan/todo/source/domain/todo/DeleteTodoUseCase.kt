package com.sirinan.todo.source.domain.todo

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.todo.TodoStatusItem
import com.sirinan.todo.source.model.todo.toItem
import com.sirinan.todo.source.remote.repository.TodoRepository

class DeleteTodoUseCase(private val repository: TodoRepository) {

    suspend fun execute(id: String, token: String): CallBackResponse<TodoStatusItem> {
        val response = repository.delete(id, token)
       return response.makeCallback { it.toItem() }
    }
}