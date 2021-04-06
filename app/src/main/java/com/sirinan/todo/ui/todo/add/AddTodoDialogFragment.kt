package com.sirinan.todo.ui.todo.add

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sirinan.todo.R
import com.sirinan.todo.core.AbstractDialogFragment
import com.sirinan.todo.databinding.DialogAddTodoBinding
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.utils.*

class AddTodoDialogFragment : AbstractDialogFragment<AddTodoViewModel, DialogAddTodoBinding>
    (AddTodoViewModel::class, DialogAddTodoBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance(editPage: Boolean) = AddTodoDialogFragment().withArguments {
            putBoolean("editPage", editPage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonAdd.setOnClickListener(::onClickListener)
            buttonCancel.setOnClickListener(::onClickListener)
        }
        viewModel.getArgument(arguments)
    }
    override fun onStart() {
        super.onStart()
        dialog.setScreenWidthFull()
    }

    override fun onSubscribe() {
        super.onSubscribe()
        observe(viewModel.showError, ::showTextError)
        observe(viewModel.callBackAddData, ::callbackAddData)
        observe(viewModel.callBackEditData, ::callbackEditData)
        observe(viewModel.editTodoModel, ::updateTodo)
        observe(viewModel.callBackNotChange, ::dismiss)
    }

    private fun getInput() {
        viewModel.validateInput(binding.editDescription.textOrEmpty)

    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.buttonAdd -> getInput()
            R.id.buttonCancel -> dismiss()
        }
    }

    private fun showTextError(visibility: Int) {
        binding.textValidate.visibility = visibility
    }

    private fun callbackAddData(input: String) {
        setResultTarget(101) {
            putExtra("description", input)
        }
        dismiss()
    }

    private fun callbackEditData(input: String) {
        setResultTarget(102) {
            putExtra("description", input)
        }
        dismiss()
    }

    private fun updateTodo(model: TodoItem) {
        binding.buttonAdd.text="save"
        binding.editDescription.setText(model.description)
    }
}