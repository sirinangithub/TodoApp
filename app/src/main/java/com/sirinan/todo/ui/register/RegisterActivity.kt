package com.sirinan.todo.ui.register

import androidx.fragment.app.Fragment
import com.sirinan.todo.core.BaseFragmentActivity
import com.sirinan.todo.databinding.ActivityBaseBinding

class RegisterActivity : BaseFragmentActivity<RegisterViewModel, ActivityBaseBinding>(
    RegisterViewModel::class,
    ActivityBaseBinding::inflate){

    override fun fragment(): Fragment{
        return RegisterFragment.newInstance()
    }

}
