package com.sirinan.todo.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object MessageDialog {

    fun ok(
        context: Context?,
        message: String?,
        ok: (() -> Unit)? = null,
        cancel: (() -> Unit)? = null
    ) {
        if (context != null) {
            MaterialAlertDialogBuilder(context).apply {
                setMessage(message)
                setPositiveButton(context.getString(android.R.string.ok)) { _, _ ->
                    ok?.invoke()
                }
                setOnCancelListener {
                    cancel?.invoke()
                }

            }
                .show()
        }

    }

}