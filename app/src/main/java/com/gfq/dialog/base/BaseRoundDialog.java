package com.gfq.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.gfq.dialog.util.DensityUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


/**
 * create by 高富强
 * on {2019/10/15} {17:22}
 * desctapion:
 */
public abstract class BaseRoundDialog<T extends ViewDataBinding>{
    private Context context;
    private Dialog dialog;
    private Window window;
    private LayoutInflater layoutInflater;
    protected T dgBinding;
    private Drawable backgroundDrawable;
    private @DrawableRes
    int backgroundDrawableResource;
    private int gravity = Gravity.CENTER;
    private int horizontalMargin = 0;
    private int verticalMargin = 0;
    /**
     * 默认背景色 白色
     */
    private int backgroundColor = Color.WHITE;

    /**
     * 默认圆角 5dp
     */
    private int radius = DensityUtil.dp2px(5);
    /**
     * 默认宽度 = 屏幕宽度 * 0.8
     */
    private float widthPercent = 0.8f;
    /**
     * 默认高度 = 包裹内容
     */
    private float heightPercent = 0;


    public BaseRoundDialog(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        init();
    }


    private void init() {
        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        dialog = new Dialog(context);
        window = dialog.getWindow();

        dialog.setContentView(dgBinding.getRoot());
        dialog.setCanceledOnTouchOutside(true);


        //  设置默认样式
        setWidthPercent(0.8f);
        setBackgroundColor(Color.WHITE);
        setRadius(radius);

        bindView();
    }

    protected abstract @LayoutRes
    int layout();

    protected abstract void bindView();


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
    public void setAttributes() {
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            Display mDisplay = window.getWindowManager().getDefaultDisplay();// 获取屏幕宽、高用

//            lp.x = 100; // 新位置X坐标
//            lp.y = 100; // 新位置Y坐标
//            lp.width = 300; // 宽度
//            lp.height = 300; // 高度
//            lp.alpha = 0.7f; // 透明度

            lp.x = horizontalMargin;
            lp.y = verticalMargin;

            // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
            // dialog.onWindowAttributesChanged(lp);
            // mDialogWindow.setAttributes(lp);

            // 4 将对话框的大小按屏幕大小的百分比设置
            Point point = new Point();
            mDisplay.getRealSize(point);

            if (widthPercent > 0) {
                lp.width = (int) (point.x * widthPercent);
            }

            if (heightPercent > 0) {
                lp.height = (int) (point.y * heightPercent);
            }

            if (gravity != 0) {
                window.setGravity(gravity);
            }

            window.setAttributes(lp);

        }
    }

    public void setWidthPercent(float widthPercent) {
        this.widthPercent = widthPercent;
        setAttributes();
    }

    public void setHeightPercent(float heightPercent) {
        this.heightPercent = heightPercent;
        setAttributes();
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        setAttributes();
    }

    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = DensityUtil.dp2px(horizontalMargin);
        setAttributes();
    }


    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = DensityUtil.dp2px(verticalMargin);
        setAttributes();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        setRadiusOrBackgroundColor();
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == null) return;
        this.backgroundDrawable = drawable;
        if (window != null) {
            window.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundDrawableResource(@DrawableRes int backgroundDrawableResource) {
        if (backgroundDrawableResource == 0) return;
        this.backgroundDrawableResource = backgroundDrawableResource;
        if (window != null) {
            window.setBackgroundDrawableResource(backgroundDrawableResource);
        }
    }

    public void setBackgroundColor(@ColorInt int color) {
        backgroundColor = color;
        setRadiusOrBackgroundColor();
    }

    private void setRadiusOrBackgroundColor() {
        if (window != null) {
            window.setBackgroundDrawable(getDrawable(DensityUtil.dp2px(radius), backgroundColor));
        }
    }

    private Drawable getDrawable(int radius, @ColorInt int color) {
        float[] outerR = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        RoundRectShape roundRectShape = new RoundRectShape(outerR, null, null);
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        drawable.setTint(color);
        return drawable;
    }
    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setCanceledOnTouchOutside(boolean boo) {
        dialog.setCanceledOnTouchOutside(boo);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }

    public float getWidthPercent() {
        return widthPercent;
    }

    public float getHeightPercent() {
        return heightPercent;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getRadius() {
        return radius;
    }

    public int getGravity() {
        return gravity;
    }

    public int getHorizontalMargin() {
        return horizontalMargin;
    }

    public int getVerticalMargin() {
        return verticalMargin;
    }

    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public int getBackgroundDrawableResource() {
        return backgroundDrawableResource;
    }
}


