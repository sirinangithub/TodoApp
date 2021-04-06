package com.sirinan.todo.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sirinan.todo.utils.MessageDialog
import com.sirinan.todo.utils.makeToast
import com.sirinan.todo.utils.observe
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    clazz: KClass<VM>,
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> VB
) : AbstractFragment<VM, VB>(clazz, bindingInflater) {


    override fun onSubscribe() {
        observe((viewModel as BaseViewModel).failureMessage, ::showDialogMessage)
        observe((viewModel as BaseViewModel).failureRes, ::showDialogMessageResource)
        observe((viewModel as BaseViewModel).progress, ::showProgress)
        observe((viewModel as BaseViewModel).progressDialog, ::showDialogProgress)
        observe((viewModel as BaseViewModel).toast, ::showToast)
    }


    fun showDialogMessage(message: String) {
        MessageDialog.ok(context, message)
    }

    fun showDialogMessageResource(messageRes: Int) {
        MessageDialog.ok(context, getString(messageRes))
    }

    fun showToast(message: String) {
        context?.let { it.makeToast(message) }
    }

    fun showDialogProgress(show: Boolean) {
//        if (show) progressDialog?.show() else progressDialog?.dismiss()
    }
    open fun showProgress(show: Boolean) {}
}