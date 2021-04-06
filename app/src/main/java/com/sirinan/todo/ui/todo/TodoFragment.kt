package com.sirinan.todo.ui.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirinan.todo.R
import com.sirinan.todo.core.BaseFragment
import com.sirinan.todo.databinding.FragmentTodoBinding
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.ui.register.RegisterActivity
import com.sirinan.todo.ui.todo.add.AddTodoDialogFragment
import com.sirinan.todo.ui.todo.adapter.TodoAdapter
import com.sirinan.todo.ui.todo.add.AddTodoViewModel
import com.sirinan.todo.ui.todo.detail.DetailTodoDialogFragment
import com.sirinan.todo.ui.todo.detail.DetailTodoViewModel
import com.sirinan.todo.utils.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.prefs.AbstractPreferences

class TodoFragment : BaseFragment<TodoViewModel, FragmentTodoBinding>(
    TodoViewModel::class,
    FragmentTodoBinding::inflate
) {

    private val detailTodoViewModel: DetailTodoViewModel by sharedViewModel()
    private val addTodoViewModel: AddTodoViewModel by sharedViewModel()
    private val adapter: TodoAdapter by inject()

    companion object {
        @JvmStatic
        fun newInstance() = TodoFragment().withArguments {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setAdapter()
        filterArray()
        loadTodo()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) addTodo(data)
        if (requestCode == 102 && resultCode == Activity.RESULT_OK) updateTodo(data)
    }

    private fun filterArray() {
        val arrayFirst = arrayOf(1,2,3,4,5,6)
        val arraySecond = arrayOf(9)
        val arrayFilter = arrayListOf<Int>()

        arrayFirst.forEach { first->
            arraySecond.forEach { second->
                if(first==second)arrayFilter.add(first)
            }
        }
        logDebug("$arrayFilter")
    }

    override fun onSubscribe() {
        super.onSubscribe()
        observe(viewModel.todoModels, ::showTodoList)
        observe(viewModel.addTodoModel, ::addTodoList)
        observe(viewModel.updateTodoModel, ::updateTodoList)
        observe(viewModel.deleteTodoModel, ::deleteTodoList)
    }

    private fun setupView() {
        binding.run {
            buttonAdd.setOnClickListener(::onClickListener)
            buttonEdit.setOnClickListener(::onClickListener)
            buttonCancel.setOnClickListener(::onClickListener)
            buttonDelete.setOnClickListener(::onClickListener)
            buttonLogout.setOnClickListener(::onClickListener)
        }
    }

    private fun setAdapter() {
        adapter.setOnClickListener(::onItemClickListener)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TodoFragment.adapter
        }
    }

    override fun showProgress(show: Boolean) {
        binding.layoutProgress.gone(!show)
    }


    private fun loadTodo() {
        viewModel.getAllTodo()
    }

    private fun showAddTodoDialog() {
        AddTodoDialogFragment.newInstance(false).targetWithShow(this, 101)
    }

    private fun showEditTodoDialog() {
        showMenuBottom(false)
        addTodoViewModel.todoTempModel = viewModel.modelSelected
        AddTodoDialogFragment.newInstance(true).targetWithShow(this, 102)
    }

    private fun showTodoList(models: MutableList<TodoItem>) {
        adapter.models = models
        adapter.notifyDataSetChanged()
    }

    private fun addTodoList(model: TodoItem) {
        adapter.models.add(0, model)
        adapter.notifyItemInserted(0)
    }

    private fun updateTodoList(model: TodoItem) {
        if (viewModel.positionSelect != -1) {
            adapter.models[viewModel.positionSelect] = model
            adapter.notifyItemChanged(viewModel.positionSelect)
        }
    }

    private fun deleteTodoList(position: Int) {
        adapter.models.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun addTodo(data: Intent?) {
        viewModel.addTodo(data)
    }

    private fun updateTodo(data: Intent?) {
        viewModel.upDateTodo(data)
    }

    private fun showMenuBottom(show: Boolean) {
        if (show) {
            binding.buttonAdd.hide()
            binding.buttonEdit.show()
            binding.buttonDelete.show()
            binding.buttonCancel.show()
        } else {
            binding.buttonEdit.hide()
            binding.buttonDelete.hide()
            binding.buttonCancel.hide()
            binding.buttonAdd.show()

        }
    }

    private fun deleteTodo() {
        showMenuBottom(false)
        viewModel.modelSelected?.let {
            viewModel.deleteTodo(it.id)
        }
    }

    private fun logout() {
        PreferencesUtils.instant.logout()
        launchActivity<RegisterActivity>()
        activity?.finish()
    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.buttonAdd -> showAddTodoDialog()
            R.id.buttonEdit -> showEditTodoDialog()
            R.id.buttonDelete -> deleteTodo()
            R.id.buttonCancel -> showMenuBottom(false)
            R.id.buttonLogout -> logout()
        }
    }

    private fun onItemClickListener(position: Int, model: TodoItem, isLogClick: Boolean) {
        if (isLogClick) {
            viewModel.onItemClickListener(position, model)
            showMenuBottom(true)
        } else {
            detailTodoViewModel.todoTempModel = model
            DetailTodoDialogFragment().targetWithShow(this, 103)
        }
    }


}