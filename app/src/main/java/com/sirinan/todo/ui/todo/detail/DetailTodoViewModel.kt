package com.sirinan.todo.ui.todo.detail

import com.sirinan.todo.core.BaseViewModel
import com.sirinan.todo.core.SingleLiveEvent
import com.sirinan.todo.source.domain.todo.GetAllTodoUseCase
import com.sirinan.todo.source.model.todo.TodoItem

class DetailTodoViewModel(private val getAllTodoUseCase: GetAllTodoUseCase) : BaseViewModel() {

    val todoModel = SingleLiveEvent<TodoItem>()
    var todoTempModel: TodoItem? = null


    fun getTodo() {
        todoTempModel?.let {
            todoModel.postValue(it)
        }
    }

}