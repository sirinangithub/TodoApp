package com.sirinan.todo.core

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    var failureMessage = SingleLiveEvent<String>()
        private set
    var failureRes = SingleLiveEvent<Int>()
        private set
    var toast = SingleLiveEvent<String>()
        private set
    var progress = SingleLiveEvent<Boolean>()
        private set
    var progressDialog = SingleLiveEvent<Boolean>()
        private set

    fun handleFailed(message: String) {
        showProgress(false)
        failureMessage.postValue(message)
    }

    fun handleFailed(resId: Int) {
        showProgress(false)
        failureRes.postValue(resId)
    }

    fun showToast(message: String) {
        showProgress(false)
        toast.postValue(message)
    }

    fun showProgress(show: Boolean) {
        progress.postValue(show)
    }
}