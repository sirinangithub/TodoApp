package com.sirinan.todo.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sirinan.todo.R
import kotlin.reflect.KClass

abstract class BaseFragmentActivity <VM : ViewModel, VB : ViewBinding>(
    clazz: KClass<VM>,
    bindingInflater: ((LayoutInflater) -> VB)
) : AbstractActivity<VM, VB>(clazz,bindingInflater) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSubscribe()
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag("container") == null) {
            super.onBackPressed()
        } else {
            if ((supportFragmentManager.findFragmentByTag("container") as? OnBackPressed)?.onBackPressed() == false)
                super.onBackPressed()
        }
    }

    open fun fragment(): Fragment? = null

    private fun addFragment(savedInstanceState: Bundle?) = fragment()?.let {
        savedInstanceState ?: supportFragmentManager.commit {
            replace(R.id.fragmentContainer, it, "container")
        }
    }
}