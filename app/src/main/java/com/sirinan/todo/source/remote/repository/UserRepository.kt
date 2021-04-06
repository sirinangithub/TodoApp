package com.sirinan.todo.source.remote.repository

import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.core.callback
import com.sirinan.todo.source.model.login.LoginBody
import com.sirinan.todo.source.model.register.RegisterBody
import com.sirinan.todo.source.model.register.UsesWithTokenModel
import com.sirinan.todo.source.model.status.StatusModel
import com.sirinan.todo.source.remote.endpoint.UserService
import com.sirinan.todo.source.remote.map.AuthorizationHeader


interface UserRepository {
    suspend fun register(param: RegisterBody): CallBackResponse<UsesWithTokenModel>
    suspend fun login(param: LoginBody): CallBackResponse<UsesWithTokenModel>
    suspend fun logout(param: String): CallBackResponse<StatusModel>
}

class UserRepositoryImpl(private val service: UserService) : UserRepository {

    override suspend fun register(param: RegisterBody): CallBackResponse<UsesWithTokenModel> {
        return service.register(param).callback()
    }

    override suspend fun login(param: LoginBody): CallBackResponse<UsesWithTokenModel> {
        return service.login(param).callback()
    }

    override suspend fun logout(param: String): CallBackResponse<StatusModel> {
        return service.logout(AuthorizationHeader(param)).callback()
    }
}

