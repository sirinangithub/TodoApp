package com.sirinan.todo.source.remote.endpoint

import com.sirinan.todo.source.model.todo.AddTodoBody
import com.sirinan.todo.source.model.todo.TodoCountModel
import com.sirinan.todo.source.model.todo.TodoStatusModel
import com.sirinan.todo.source.model.todo.UpdateTodoBody
import com.sirinan.todo.source.remote.map.AuthorizationHeader
import com.sirinan.todo.source.remote.network.ServiceProperties
import retrofit2.Response
import retrofit2.http.*

interface TodoService {

    @POST(ServiceProperties.Task)
    suspend fun addTodo(
        @Body
        body: AddTodoBody,
        @HeaderMap
        header: AuthorizationHeader
    ): Response<TodoStatusModel>

    @GET(ServiceProperties.Task)
    suspend fun getAllTodo(
        @HeaderMap
        header: AuthorizationHeader
    ): Response<TodoCountModel>

    @GET(ServiceProperties.TaskId)
    suspend fun getTodo(
        @Path(value = "id")
        id: String,
        @HeaderMap
        header: AuthorizationHeader
    ): Response<TodoStatusModel>

    @PUT(ServiceProperties.TaskId)
    suspend fun updateTodo(
        @Path(value = "id")
        id: String,
        @Body
        body: UpdateTodoBody,
        @HeaderMap
        header: AuthorizationHeader
    ): Response<TodoStatusModel>


    @DELETE(ServiceProperties.TaskId)
    suspend fun deleteTodo(
        @Path(value = "id")
        id: String,
        @HeaderMap
        header: AuthorizationHeader
    ): Response<TodoStatusModel>
}