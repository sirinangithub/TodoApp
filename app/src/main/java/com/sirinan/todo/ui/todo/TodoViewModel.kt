package com.sirinan.todo.ui.todo

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.sirinan.todo.core.BaseViewModel
import com.sirinan.todo.core.SingleLiveEvent
import com.sirinan.todo.source.domain.todo.AddTodoUseCase
import com.sirinan.todo.source.domain.todo.DeleteTodoUseCase
import com.sirinan.todo.source.domain.todo.GetAllTodoUseCase
import com.sirinan.todo.source.domain.todo.UpdateTodoUseCase
import com.sirinan.todo.source.model.todo.AddTodoBody
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.source.model.todo.TodoStatusItem
import com.sirinan.todo.source.model.todo.UpdateTodoBody
import com.sirinan.todo.utils.PreferencesUtils
import com.sirinan.todo.utils.stringEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel(
    private val getAllTodoUseCase: GetAllTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : BaseViewModel() {

    val todoModels = SingleLiveEvent<MutableList<TodoItem>>()
    val updateTodoModel = SingleLiveEvent<TodoItem>()
    val addTodoModel = SingleLiveEvent<TodoItem>()
    val deleteTodoModel = SingleLiveEvent<Int>()
    var positionSelect = -1
    var modelSelected: TodoItem? = null

    fun getAllTodo() {
        showProgress(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAllTodoUseCase.execute(PreferencesUtils.instant.token)
            }.callback(::handleAllTodoCallBack, ::handleFailed)
        }
    }

    private fun handleAllTodoCallBack(models: MutableList<TodoItem>) {
        showProgress(false)
        todoModels.postValue(models)
    }

    fun upDateTodo(data: Intent?) {
        showProgress(true)
        val description = data.stringEmpty("description")
        val body = UpdateTodoBody(true, description)
        val id = modelSelected?.id ?: ""
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateTodoUseCase.execute(id, body, PreferencesUtils.instant.token)
            }.callback(::handleUpdateCallBack, ::handleFailed)
        }
    }

    private fun handleUpdateCallBack(model: TodoStatusItem) {
        showProgress(false)
        if (model.success && model.data != null) {
            updateTodoModel.postValue(model.data)
        }
    }


    fun addTodo(data: Intent?) {
        showProgress(true)
        val description = data.stringEmpty("description")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                addTodoUseCase.execute(AddTodoBody(description), PreferencesUtils.instant.token)
            }.callback(::handleAddTodoCallBack, ::handleFailed)
        }
    }

    private fun handleAddTodoCallBack(model: TodoStatusItem) {
        showProgress(false)
        if (model.success && model.data != null) {
            addTodoModel.postValue(model.data)
        }
    }

    fun deleteTodo(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteTodoUseCase.execute(id, PreferencesUtils.instant.token)
            }.callback(::handleDeleteTodoCallBack, ::handleFailed)
        }
    }

    private fun handleDeleteTodoCallBack(model: TodoStatusItem) {
        if (model.success && positionSelect != -1) {
            deleteTodoModel.postValue(positionSelect)
        }
    }

    fun onItemClickListener(position: Int, model: TodoItem) {
        positionSelect = position
        modelSelected = model
    }
}