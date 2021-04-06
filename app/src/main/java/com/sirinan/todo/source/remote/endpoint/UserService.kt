package com.sirinan.todo.source.remote.endpoint

import com.sirinan.todo.source.model.login.LoginBody
import com.sirinan.todo.source.model.register.RegisterBody
import com.sirinan.todo.source.model.register.UsesWithTokenModel
import com.sirinan.todo.source.model.status.StatusModel
import com.sirinan.todo.source.remote.map.AuthorizationHeader
import com.sirinan.todo.source.remote.map.ContentTypeHeader
import com.sirinan.todo.source.remote.network.ServiceProperties
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @POST(ServiceProperties.Register)
    suspend fun register(
        @Body
        body :RegisterBody,
        @HeaderMap
        header: ContentTypeHeader = ContentTypeHeader()
    ): Response<UsesWithTokenModel>

    @POST(ServiceProperties.Login)
    suspend fun login(
        @Body
        body : LoginBody,
        @HeaderMap
        header: ContentTypeHeader = ContentTypeHeader()
    ): Response<UsesWithTokenModel>

    @POST(ServiceProperties.Logout)
    suspend fun logout(
        @HeaderMap
        header: AuthorizationHeader
    ): Response<StatusModel>
}