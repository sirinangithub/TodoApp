package com.sirinan.todo.ui.todo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirinan.todo.source.model.todo.TodoItem
import com.sirinan.todo.ui.todo.holder.TodoViewHolder

class TodoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var models = mutableListOf<TodoItem>()
    private var clickListener: ((Int, TodoItem,Boolean) -> Unit)? = null

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoViewHolder -> holder.onBind(models[position]) { item, option ->
                clickListener?.invoke(holder.absoluteAdapterPosition, item,option)
            }

        }
    }

     fun setOnClickListener(clickListener: ((Int, TodoItem,Boolean) -> Unit)?) {
        this.clickListener = clickListener
    }

}