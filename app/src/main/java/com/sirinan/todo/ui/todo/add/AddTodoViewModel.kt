package com.sirinan.todo.ui.todo.add

import android.os.Bundle
import android.view.View
import com.sirinan.todo.core.BaseViewModel
import com.sirinan.todo.core.SingleLiveEvent
import com.sirinan.todo.source.domain.todo.GetAllTodoUseCase
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.utils.booleanDefault

class AddTodoViewModel(private val getAllTodoUseCase: GetAllTodoUseCase) : BaseViewModel() {

    var todoTempModel: TodoItem? = null
    val showError = SingleLiveEvent<Int>()
    val editTodoModel = SingleLiveEvent<TodoItem>()
    val callBackAddData = SingleLiveEvent<String>()
    val callBackEditData = SingleLiveEvent<String>()
    val callBackNotChange = SingleLiveEvent<Unit>()
    private var editPage = false

    fun validateInput(input: String) {
        if (input.isEmpty()) {
            showError.postValue(View.VISIBLE)
        } else {
            if (editPage) {
                if (input != todoTempModel?.description)
                    callBackEditData.postValue(input)
                else
                    callBackNotChange.call()
            } else {
                callBackAddData.postValue(input)
            }
        }

    }

    fun getArgument(arguments: Bundle?) {
        editPage = arguments.booleanDefault("editPage")
        if (editPage && todoTempModel != null)
            editTodoModel.postValue(todoTempModel)
    }

}