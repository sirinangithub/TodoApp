package com.sirinan.todo.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


 fun <T : Any, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L,  body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

inline fun <T : Any, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, crossinline body: () -> Unit) =
    liveData.observe(this, Observer{body()})