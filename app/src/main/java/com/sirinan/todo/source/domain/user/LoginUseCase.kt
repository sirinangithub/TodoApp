package com.sirinan.todo.source.domain.user

import com.sirinan.todo.core.BaseUseCase
import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.login.LoginBody
import com.sirinan.todo.source.model.register.UsesWithTokenModel
import com.sirinan.todo.source.remote.repository.UserRepository

class LoginUseCase(private val repository: UserRepository) :
    BaseUseCase<LoginBody, UsesWithTokenModel>() {

    override suspend fun execute(params: LoginBody): CallBackResponse<UsesWithTokenModel> {
        return repository.login(params)
    }
}