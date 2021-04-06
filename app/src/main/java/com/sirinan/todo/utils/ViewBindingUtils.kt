package com.sirinan.todo.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.viewBinding(
    crossinline bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T,
    attachToParent: Boolean = false
) =
    bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent)

inline fun <T : ViewBinding> ViewGroup.viewBindingLazy(
    crossinline bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T,
    attachToParent: Boolean = true
) = lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent) }

