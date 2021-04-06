package com.sirinan.todo.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sirinan.todo.core.navigation.FragmentNavigation
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

abstract class AbstractFragment<VM : ViewModel, VB : ViewBinding>(
    clazz: KClass<VM>,
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> VB
) : Fragment(), OnBackPressed {

    private var mutableBinding: VB? = null
    open val viewModel: VM by sharedViewModel(clazz = clazz)
    val binding: VB get() = mutableBinding!!
    var fragmentNavigation: FragmentNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment != null && parentFragment is FragmentNavigation) {
            fragmentNavigation = parentFragment as FragmentNavigation
        }
    }

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

    override fun onBackPressed(): Boolean {
        return false
    }

    open fun onSubscribe() {}

    open fun includeBinding(root: View?) {}
}