package com.sirinan.todo.source.remote.repository

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.core.callback
import com.sirinan.todo.source.model.todo.AddTodoBody
import com.sirinan.todo.source.model.todo.TodoCountModel
import com.sirinan.todo.source.model.todo.TodoStatusModel
import com.sirinan.todo.source.model.todo.UpdateTodoBody
import com.sirinan.todo.source.remote.endpoint.TodoService
import com.sirinan.todo.source.remote.map.AuthorizationHeader


interface TodoRepository {
    suspend fun add(body: AddTodoBody,token:String): CallBackResponse<TodoStatusModel>
    suspend fun update(id:String,body: UpdateTodoBody,token:String): CallBackResponse<TodoStatusModel>
    suspend fun delete(id:String,token:String): CallBackResponse<TodoStatusModel>
    suspend fun getAll(token:String): CallBackResponse<TodoCountModel>
    suspend fun getById(id:String,token:String): CallBackResponse<TodoStatusModel>
}

class TodoRepositoryImpl(private val service: TodoService) : TodoRepository {

    override suspend fun add(body: AddTodoBody, token: String)
    : CallBackResponse<TodoStatusModel> {
        return service.addTodo(body, AuthorizationHeader(token)).callback()
    }

    override suspend fun getAll(token: String): CallBackResponse<TodoCountModel> {
        return service.getAllTodo(AuthorizationHeader(token)).callback()
    }

    override suspend fun update(
        id: String,
        body: UpdateTodoBody,
        token: String
    ): CallBackResponse<TodoStatusModel> {
        return service.updateTodo(id,body, AuthorizationHeader(token)).callback()
    }

    override suspend fun getById(id: String, token: String): CallBackResponse<TodoStatusModel> {
        return service.getTodo(id,AuthorizationHeader(token)).callback()
    }

    override suspend fun delete(id: String, token: String): CallBackResponse<TodoStatusModel> {
        return service.deleteTodo(id,AuthorizationHeader(token)).callback()
    }
}

