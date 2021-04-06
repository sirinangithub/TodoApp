package com.sirinan.todo.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <T : Fragment> T.withArguments(argsBuilder: Bundle.() -> Unit): T =
        this.apply { arguments = Bundle().apply(argsBuilder) }

inline fun <reified T : FragmentActivity> Fragment.launchActivity(bundle: Bundle? = null, block: Intent.() -> Unit = {}) {
        val intent = Intent(activity, T::class.java)
        block(intent)
        startActivity(intent,bundle)
}
inline fun Fragment.setResultTarget(requestCode: Int, func: Intent.() -> Unit = {}) {
        val intent = Intent()
        func(intent)
        targetFragment?.onActivityResult(requestCode, Activity.RESULT_OK, intent)
}
