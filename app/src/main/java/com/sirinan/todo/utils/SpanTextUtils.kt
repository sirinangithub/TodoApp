package com.sirinan.todo.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.widget.TextView
import androidx.annotation.DimenRes

object SpanTextUtils {

    fun size(context: Context?, text: String, @DimenRes sizeRes: Int): Spannable {
        val textSize = context?.resources?.getDimensionPixelSize(sizeRes)?:0
        val spannable: Spannable = SpannableString(text)
        spannable.setSpan(
            AbsoluteSizeSpan(textSize),
            0,
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    fun showText(view: TextView, vararg span: Spannable) {
        val spanStringBuilder = SpannableStringBuilder()
        for (spannable in span) {
            spanStringBuilder.append(spannable)
        }
        view.setText(spanStringBuilder, TextView.BufferType.SPANNABLE)
    }


}