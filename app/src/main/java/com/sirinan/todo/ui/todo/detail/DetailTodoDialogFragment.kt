package com.sirinan.todo.ui.todo.detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.sirinan.todo.R
import com.sirinan.todo.core.AbstractDialogFragment
import com.sirinan.todo.databinding.DialogAddTodoBinding
import com.sirinan.todo.databinding.DialogDetailTodoBinding
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.utils.observe
import com.sirinan.todo.utils.setResultTarget
import com.sirinan.todo.utils.setScreenWidthFull
import com.sirinan.todo.utils.textOrEmpty

class DetailTodoDialogFragment :
    AbstractDialogFragment<DetailTodoViewModel, DialogDetailTodoBinding>
        (DetailTodoViewModel::class, DialogDetailTodoBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonDone.setOnClickListener(::onClickListener)
        }
        getTodo()
    }

    override fun onStart() {
        super.onStart()
        dialog.setScreenWidthFull()
    }

    override fun onSubscribe() {
        super.onSubscribe()
        observe(viewModel.todoModel, ::showTodo)
    }

    private fun getTodo() {
        viewModel.getTodo()
    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.buttonDone -> dismiss()
        }
    }

    private fun showTodo(model: TodoItem) {
        binding.run {
            textDate.text = model.updatedDate
            textTime.text = model.updatedTime
            textDescription.text = model.description
        }
    }


}