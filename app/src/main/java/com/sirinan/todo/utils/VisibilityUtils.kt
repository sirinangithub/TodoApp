package com.sirinan.todo.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.gone(isGone: Boolean) {
    this.visibility = if (isGone) View.GONE else View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.invisible(isInvisible: Boolean) {
    this.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

fun View.disable() {
    this.isEnabled = false
}
fun View.enable() {
    this.isEnabled = true
}
fun View.enableWithAlpha(isEnable:Boolean) {
    if(isEnable){
        this.alpha = 1f
        this.isEnabled = true
    }else{
        this.alpha = 0.3f
        this.isEnabled = false
    }
}
