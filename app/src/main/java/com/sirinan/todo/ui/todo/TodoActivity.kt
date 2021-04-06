package com.sirinan.todo.ui.todo

import androidx.fragment.app.Fragment
import com.sirinan.todo.core.BaseFragmentActivity
import com.sirinan.todo.databinding.ActivityBaseBinding

class TodoActivity : BaseFragmentActivity<TodoViewModel, ActivityBaseBinding>(
    TodoViewModel::class,
    ActivityBaseBinding::inflate){

    override fun fragment(): Fragment{
        return TodoFragment.newInstance()
    }

}
