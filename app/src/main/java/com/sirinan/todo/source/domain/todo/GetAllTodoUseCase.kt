package com.sirinan.todo.source.domain.todo

import com.sirinan.todo.core.BaseUseCase
import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.todo.*
import com.sirinan.todo.source.remote.repository.TodoRepository

class GetAllTodoUseCase(private val repository: TodoRepository) :
    BaseUseCase<String, MutableList<TodoItem>>() {

    override suspend fun execute(token: String): CallBackResponse<MutableList<TodoItem>> {
        val response = repository.getAll(token)
        return response.makeCallback { it.data.toItem() }
    }


}