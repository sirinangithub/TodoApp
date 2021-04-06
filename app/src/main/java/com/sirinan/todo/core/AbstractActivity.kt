package com.sirinan.todo.core

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class AbstractActivity<VM : ViewModel, VB : ViewBinding>(
    clazz: KClass<VM>,
    private val bindingInflater: ((LayoutInflater) -> VB)? = null
) : AppCompatActivity() {

    private var mutableBinding: VB? = null
    val viewModel: VM by viewModel(clazz = clazz)
    val binding: VB get() = mutableBinding!!


    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT

        bindingInflater?.let {
            mutableBinding = bindingInflater.invoke(layoutInflater)
            includeBinding(mutableBinding?.root)
            setContentView(mutableBinding?.root)
        }
        onSubscribe()
    }

    open fun onSubscribe() {}

    open fun includeBinding(root: View?) {}

    override fun onDestroy() {
        super.onDestroy()
        mutableBinding = null
    }
}