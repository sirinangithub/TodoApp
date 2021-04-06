package com.sirinan.todo.source.domain.todo

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.todo.TodoStatusModel
import com.sirinan.todo.source.remote.repository.TodoRepository

class GetTodoByIdUseCase(private val repository: TodoRepository) {

    suspend fun execute(id: String, token: String): CallBackResponse<TodoStatusModel> {
        return repository.getById(id, token)
    }
}