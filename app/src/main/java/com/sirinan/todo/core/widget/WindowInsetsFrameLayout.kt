package com.sirinan.todo.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

class WindowInsetsFrameLayout @JvmOverloads constructor(context: Context,
                                                        attrs: AttributeSet? = null,
                                                        defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets? {
        childCount.let {
            for (index in 0 until it) {
                getChildAt(index).dispatchApplyWindowInsets(insets)
            }
        }
        return insets
    }
}