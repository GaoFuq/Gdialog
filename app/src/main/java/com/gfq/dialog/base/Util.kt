package com.gfq.dialog.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.TypedValue

/**
 * DATE_TIME 2021/2/2
 * AUTH gaofuq
 * DESCRIPTION
 */


/**
 * dp转px
 */
fun dp2px(context: Context,dpValue: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
            context.applicationContext.resources.displayMetrics
    ).toInt()
}

/**
 * sp转px
 */
fun sp2px(context: Context,spValue: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        spValue,
       context.applicationContext.resources.displayMetrics
    ).toInt()
}

fun isUIThread(): Boolean {
    return Thread.currentThread() == Looper.getMainLooper().thread
}

private val mainThreadHandler: Handler by lazy {
    Handler(Looper.getMainLooper())
}

fun runOnUiThread(func: () -> Unit) {
    if (isUIThread()) func() else mainThreadHandler.post { func() }
}