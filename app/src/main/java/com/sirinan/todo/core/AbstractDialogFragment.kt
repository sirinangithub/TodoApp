package com.sirinan.todo.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

abstract class AbstractDialogFragment<VM : ViewModel, VB : ViewBinding>(
    clazz: KClass<VM>,
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> VB
) : DialogFragment() {

    private var mutableBinding: VB? = null
    open val viewModel: VM by sharedViewModel(clazz = clazz)
    val binding: VB get() = mutableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mutableBinding = bindingInflater.invoke(inflater,container,false)
        includeBinding(mutableBinding?.root)
        return mutableBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSubscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }

    open fun onSubscribe() {}

    open fun includeBinding(root: View?) {}
}