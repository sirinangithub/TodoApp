package com.sirinan.todo.source.domain.user

import com.sirinan.todo.core.BaseUseCase
import com.sirinan.todo.core.CallBackResponse
import com.sirinan.todo.source.model.register.RegisterBody
import com.sirinan.todo.source.model.register.UsesWithTokenModel
import com.sirinan.todo.source.remote.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) :
    BaseUseCase<RegisterBody, UsesWithTokenModel>() {

    override suspend fun execute(params: RegisterBody): CallBackResponse<UsesWithTokenModel> {
        return repository.register(params)
    }
}