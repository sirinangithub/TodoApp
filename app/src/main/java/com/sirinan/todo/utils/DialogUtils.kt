package com.sirinan.todo.utils

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun DialogFragment.targetWithShow(fragment: Fragment,requestCode:Int){
    this.setTargetFragment(fragment,requestCode)
    this.show(fragment.parentFragmentManager,"dialog")
}