package com.sirinan.todo.utils

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.RectF
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlin.math.floor
import kotlin.math.roundToInt

private val displayMetrics: DisplayMetrics by lazy { Resources.getSystem().displayMetrics }

val screenRectPx: Rect
    get() = displayMetrics.run { Rect(0, 0, widthPixels, heightPixels) }

val screenRectDp: RectF
    get() = displayMetrics.run { RectF(0f, 0f, widthPixels.pxToDp, heightPixels.pxToDp) }

val Number.pxToDp: Float
    get() = this.toFloat() / displayMetrics.density

val Number.dpToPx: Int
    get() = (this.toFloat() * displayMetrics.density).roundToInt()

fun View.setViewWidth(scaleWidth: Float) {
    val screenWidth = screenRectPx.width()
    val viewWidth = floor(screenWidth / scaleWidth).toInt()

    val layoutParams = this.layoutParams
    layoutParams.width = viewWidth
    this.layoutParams = layoutParams
}
fun Dialog?.setScreenWidthFull(){
    val width = ViewGroup.LayoutParams.MATCH_PARENT
    val height = ViewGroup.LayoutParams.WRAP_CONTENT
    this?.window?.setLayout(width, height)
}