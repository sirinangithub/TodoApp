package com.sirinan.todo.source.domain.todo

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.todo.AddTodoBody
import com.sirinan.todo.source.model.todo.TodoStatusItem
import com.sirinan.todo.source.model.todo.toItem
import com.sirinan.todo.source.remote.repository.TodoRepository

class AddTodoUseCase(private val repository: TodoRepository) {

     suspend fun execute(body: AddTodoBody, token:String): CallBackResponse<TodoStatusItem> {
        val response= repository.add(body,token)
         return response.makeCallback { it.toItem() }
    }
}