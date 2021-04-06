package com.sirinan.todo.ui.todo.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirinan.todo.R
import com.sirinan.todo.databinding.ViewTodoBinding
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.utils.viewBinding


class TodoViewHolder(
    private val parent: ViewGroup,
    private val binding: ViewTodoBinding = parent.viewBinding(ViewTodoBinding::inflate)
) : RecyclerView.ViewHolder(binding.root) {


    fun onBind(
        model: TodoItem,
        clickListener: ((TodoItem, Boolean) -> Unit)?
    ) {
        binding.run {
            textDescription.text = model.description
            textDate.text = model.updatedDate
            textTime.text = model.updatedTime
            cardView.setOnClickListener { clickListener?.invoke(model, false) }
            buttonMore.setOnClickListener { clickListener?.invoke( model, true) }
        }

    }


}