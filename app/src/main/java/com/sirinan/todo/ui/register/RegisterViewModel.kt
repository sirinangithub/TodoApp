package com.sirinan.todo.ui.register

import androidx.lifecycle.viewModelScope
import com.sirinan.todo.R
import com.sirinan.todo.core.BaseViewModel
import com.sirinan.todo.core.SingleLiveEvent
import com.sirinan.todo.source.domain.user.LoginUseCase
import com.sirinan.todo.source.domain.user.RegisterUseCase
import com.sirinan.todo.source.model.login.LoginBody
import com.sirinan.todo.source.model.register.RegisterBody
import com.sirinan.todo.source.model.register.UsesWithTokenModel
import com.sirinan.todo.utils.PreferencesUtils
import com.sirinan.todo.utils.isEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val statusSuccess = SingleLiveEvent<Unit>()

    private fun callRegister(userBody: RegisterBody) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                registerUseCase.execute(userBody)
            }.callback(::handleRegisterCallBack, ::handleFailed)
        }
    }

    private fun handleRegisterCallBack(model: UsesWithTokenModel) {
        showToast("Register success")
        PreferencesUtils.instant.token = model.token
        statusSuccess.call()
    }

    private fun callLogin(userBody: LoginBody) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginUseCase.execute(userBody)
            }.callback(::handleLoginCallBack, ::handleFailed)
        }
    }

    private fun handleLoginCallBack(model: UsesWithTokenModel) {
        showToast("Login success")
        PreferencesUtils.instant.token = model.token
        statusSuccess.call()
    }

    fun validateInput(userBody: RegisterBody) {
        userBody.run {
            when {
                name.isEmpty() -> handleFailed(R.string.register_error_name_empty)
                email.isEmpty() -> handleFailed(R.string.register_error_email_empty)
                !email.isEmail -> handleFailed(R.string.register_error_email_invalid)
                password.isEmpty() -> handleFailed(R.string.register_error_password_empty)
                age < 0 -> handleFailed(R.string.register_error_password_empty)
                else -> {
                    callRegister(userBody)
                }
            }
        }
    }

    fun validateInput(userBody: LoginBody) {
        userBody.run {
            when {
                email.isEmpty() -> handleFailed(R.string.register_error_email_empty)
                !email.isEmail -> handleFailed(R.string.register_error_email_invalid)
                password.isEmpty() -> handleFailed(R.string.register_error_password_empty)
                else -> {
                    callLogin(userBody)
                }
            }
        }
    }
}