package com.sirinan.todo.core.navigation

import androidx.fragment.app.Fragment

interface FragmentNavigation {

    fun open(fragment: Fragment)

    fun replace(fragment: Fragment, addToBackStack: Boolean)

    fun navigateBack()
}
