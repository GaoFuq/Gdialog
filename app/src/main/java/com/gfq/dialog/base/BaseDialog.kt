package com.gfq.dialog.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gfq.dialog.util.DensityUtil

/**
 * create by 高富强
 * on {2019/10/15} {17:22}
 * desctapion:
 * 默认样式：
 * 4周圆角5dp，
 * 背景白色，
 * 宽度 = 屏幕宽度 * 0.8，
 * 高度包裹内容。
 *
 *
 *
 * @see show(context:Context?)  方法传入context ,延迟初始化
 */
abstract class BaseDialog<T : ViewDataBinding>(layoutId:Int,mContext: Context? = null) {
    var context: Context? = null
    var dialog: Dialog? = null
    var window: Window? = null

    var dgBinding:T = DataBindingUtil.inflate<T>(LayoutInflater.from(mContext), layoutId, null, false)


    var gravity = Gravity.CENTER
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 横向margin 单位dp
     */
    var horizontalMargin = 0
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 纵向margin 单位dp
     */
    var verticalMargin = 0
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 默认背景色 白色
     */
    var roundRectBackgroundColor = Color.WHITE
        set(value) {
            field = value
            val drawable = getRoundRectDrawable(value)
            dgBinding.root.background = drawable
        }


    /**
     * 默认圆角 5dp
     */
    var radius = DensityUtil.dp2px(5f)
        set(value) {
            field = DensityUtil.dp2px(value.toFloat())
            window?.setBackgroundDrawable(getRoundRectDrawable(Color.TRANSPARENT))
            val drawable = getRoundRectDrawable(roundRectBackgroundColor)
            dgBinding.root.background = drawable
        }

    /**
     * 宽度 占屏幕宽度 百分比
     */
    var widthPercent = 0.8f
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 高度 占屏幕高度 百分比
     */
    var heightPercent = 0f
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 固定宽度 优先级高于 百分比 单位dp
     */
    var fixedWidth = 0
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 固定高度 优先级高于 百分比 单位dp
     */
    var fixedHeight = 0
        set(value) {
            field = value
            setAttributes()
        }

    /**
     * 调光量， 范围是从1.0（完全不透明）到0.0（完全透明）
     * （框框外面的蒙层的透明度）
     */
    var dim = -1f
        set(value) {
            field = value
            setAttributes()
        }


    init {

        if (mContext != null) {
            init(mContext)
        }
    }

    private fun init(context: Context) {
        dialog = Dialog(context)
        window = dialog?.window
        dialog?.setContentView(dgBinding.root)
        dialog?.setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val background = dgBinding.root.background
        if (background == null) {
            setDefaultStyle() //  设置默认样式
        }
        bindView()
    }

    private fun setDefaultStyle() {
        widthPercent = 0.8f
        window?.setBackgroundDrawable(getRoundRectDrawable(roundRectBackgroundColor))
    }


    fun getRoundRectDrawable(backgroundColor: Int): Drawable {
        val outerR = floatArrayOf(radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat())
        val roundRectShape = RoundRectShape(outerR, null, null)
        val drawable = ShapeDrawable(roundRectShape)
        drawable.setTint(backgroundColor)
        return drawable
    }


    protected abstract fun bindView()

    /**
     * lp.x与lp.y表示相对于原始位置的偏移.
     * 当参数值包含Gravity.LEFT时,对话框出现在左边,所以lp.x就表示相对左边的偏移,负值忽略.
     * 当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略.
     * 当参数值包含Gravity.TOP时,对话框出现在上边,所以lp.y就表示相对上边的偏移,负值忽略.
     * 当参数值包含Gravity.BOTTOM时,对话框出现在下边,所以lp.y就表示相对下边的偏移,负值忽略.
     * 当参数值包含Gravity.CENTER_HORIZONTAL时,对话框水平居中,所以lp.x就表示在水平居中的位置移动lp.x像素,正值向右移动,负值向左移动.
     * 当参数值包含Gravity.CENTER_VERTICAL时,对话框垂直居中,所以lp.y就表示在垂直居中的位置移动lp.y像素,正值向右移动,负值向左移动.
     * gravity的默认值为Gravity.CENTER,即Gravity.CENTER_HORIZONTAL |
     * Gravity.CENTER_VERTICAL.
     */
    fun setAttributes() {
        if (window != null) {
            val lp = window!!.attributes
            val mDisplay = window!!.windowManager.defaultDisplay // 获取屏幕宽、高用

//            lp.x = 100; // 新位置X坐标
//            lp.y = 100; // 新位置Y坐标
//            lp.width = 300; // 宽度
//            lp.height = 300; // 高度
//            lp.alpha = 0.7f; // 透明度
            lp.x = horizontalMargin
            lp.y = verticalMargin
            if (dim >= 0) {
                lp.dimAmount = dim
            }

            // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
            // dialog.onWindowAttributesChanged(lp);
            // mDialogWindow.setAttributes(lp);

            // 4 将对话框的大小按屏幕大小的百分比设置
            val point = Point()
            mDisplay.getRealSize(point)
            if (widthPercent > 0) {
                lp.width = (point.x * widthPercent).toInt()
            }
            if (heightPercent > 0) {
                lp.height = (point.y * heightPercent).toInt()
            }
            if (fixedWidth > 0) {
                lp.width = fixedWidth
            }
            if (fixedHeight > 0) {
                lp.height = fixedHeight
            }
            if (gravity != 0) {
                window!!.setGravity(gravity)
            }
            window!!.attributes = lp
        }
    }


    fun show(context: Context? = null) {
        if (context == null) {
            if (dialog != null) {
                runOnUiThread {
                    dialog!!.show()
                }
            } else {
                Log.e("【BaseDialog Error】", "show()如果不传context，就必须在构造时传context")
            }
        } else {
            this.context = context
            context.run {
                runOnUiThread {
                    if (dialog == null) {
                        init(context)
                        dialog?.show()
                    }else{
                        dialog!!.show()
                    }
                }
            }
        }
    }

    fun dismiss() {
        dialog?.dismiss()
    }

    val isShowing = dialog?.isShowing ?: false

    fun setCanceledOnTouchOutside(boo: Boolean) {
        dialog!!.setCanceledOnTouchOutside(boo)
    }

    fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        dialog!!.setOnDismissListener(listener)
    }


}