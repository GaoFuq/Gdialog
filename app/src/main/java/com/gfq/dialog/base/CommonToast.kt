package com.gfq.dialog.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import com.gfq.dialog.R
import com.gfq.dialog.databinding.CommonToastWithImgBinding


/**
 * DATE_TIME 2021/2/7
 * AUTH gaofuq
 * DESCRIPTION
 */
object CommonToast {
    private var lastToast: Toast? = null

    @SuppressLint("ShowToast")
    private fun createToast(context: Context?, msg: Any, resId: Int): Toast {
        val currentToast = Toast.makeText(context?.applicationContext, "", Toast.LENGTH_SHORT)
        val binding: CommonToastWithImgBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.common_toast_with_img,
                null,
                false
        )
        if (resId != 0) {
            binding.ivToastImg.setImageResource(resId)
            binding.ivToastImg.visibility = View.VISIBLE
        }
        binding.tvToastMsg.text = when (msg) {
            is CharSequence -> msg
            is Int -> {
                try {
                    context?.getString(msg)
                } catch (e: Exception) {
                    msg.toString()
                }
            }
            else -> msg.toString()
        }
        if (lastToast != null) {
            lastToast!!.cancel()
        }
        lastToast = currentToast
        currentToast.view = binding.root
        currentToast.setGravity(Gravity.CENTER, 0, 0)
        return currentToast
    }

    fun show(context: Context?, msg: Any) {
        runOnUiThread { createToast(context, msg, 0).show() }
    }

    fun showWithImage(context: Context?, msg: Any, @DrawableRes icResId: Int = 0) {
        runOnUiThread { createToast(context, msg, icResId).show() }
    }

    fun cancel() {
        lastToast?.cancel()
    }

}

fun Context.showToast(msg: Any, @DrawableRes icResId: Int = 0) {
    CommonToast.showWithImage(this, msg, icResId)
}