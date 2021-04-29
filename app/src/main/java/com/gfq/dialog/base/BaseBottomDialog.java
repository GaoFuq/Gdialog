package com.gfq.dialog.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gfq.dialog.util.DensityUtil;

import org.jetbrains.annotations.NotNull;

/**
 * create by 高富强
 * on {2019/10/17} {10:05}
 * desctapion:
 */
public abstract class BaseBottomDialog<T extends ViewDataBinding> {
    /**
     * 高度 占屏幕高度 百分比
     */
    private float heightPercent;
    /**
     * 宽度 占屏幕宽度 百分比
     */
    private float widthPercent;
    /**
     * 高度 固定值 单位dp
     */
    private int fixedHeight;
    /**
     * 宽度 固定值 单位dp
     */
    private int fixedWidth;
    /**
     * 横向margin 单位dp
     */
    private int horizontalMargin;
    /**
     * 纵向margin 单位dp
     */
    private int verticalMargin;
    private int gravity = Gravity.BOTTOM;

    private final LayoutInflater layoutInflater;
    protected T dgBinding;
    private MyBottomSheetDialog dialog;
    protected final Context context;
    private int mBackgroundColor = Color.WHITE;
    private View designBottomSheet;
    /**
     * 背景轮廓 默认：左上角，右上角 radius = 10dp ，背景白色
     */
    private final MyOutLine myOutLine = new MyOutLine(DensityUtil.dp2px(10));


    public BaseBottomDialog(@NotNull Context context) {
        dialog = new MyBottomSheetDialog(context);
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        initBase();
    }

    public T getDgBinding() {
        return dgBinding;
    }

    public void setDgBinding(T dgBinding) {
        this.dgBinding = dgBinding;
    }

    private void initBase() {
        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        dialog.setContentView(dgBinding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        designBottomSheet = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (designBottomSheet != null) {
            //设置默认背景
            designBottomSheet.setBackgroundColor(Color.WHITE);
            designBottomSheet.setClipToOutline(true);
            designBottomSheet.setOutlineProvider(myOutLine);
        }
        bindView();
    }

    public MyBottomSheetDialog getDialog() {
        return dialog;
    }


    protected abstract @LayoutRes
    int layout();

    protected abstract void bindView();

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    /**
     * 设置左上角和右上角的圆角半径
     */
    public void setLeftTopAndRightTopRadius(int radius) {
        int mRadius = DensityUtil.dp2px(radius);
        if (myOutLine.getRadius() == mRadius) return;
        if (designBottomSheet != null) {
            boolean clipToOutline = designBottomSheet.getClipToOutline();
            if (!clipToOutline) {
                designBottomSheet.setClipToOutline(true);
            }
            myOutLine.setRadius(mRadius);
            myOutLine.setClipBottom(true);
        }
    }

    /**
     * 设置四个角的圆角半径
     */
    public void setFourCornerRadius(int radius) {
        int mRadius = DensityUtil.dp2px(radius);
        if (myOutLine.getRadius() == mRadius && !myOutLine.isClipBottom()) return;
        if (designBottomSheet != null) {
            boolean clipToOutline = designBottomSheet.getClipToOutline();
            if (!clipToOutline) {
                designBottomSheet.setClipToOutline(true);
            }
            myOutLine.setRadius(mRadius);
            myOutLine.setClipBottom(false);
        }
    }

    /**
     * 设置背景颜色
     */
    public void setBackgroundColor(int color) {
        if (mBackgroundColor == color) return;
        mBackgroundColor = color;
        if (designBottomSheet != null) {
            designBottomSheet.setBackgroundColor(color);
        }
    }


    /**
     * 设置dialog的高度占屏幕高度的百分比。设置后，当内容超过dialog高度时，上滑只有内容会上滑。
     */
    public void setHeightPercent(float heightPercent) {
        this.heightPercent = heightPercent;
        dialog.setHeightPercent(heightPercent);
    }

    /**
     * 设置dialog的高度 单位dp。设置后，当内容超过dialog高度时，上滑只有内容会上滑。
     */
    public void setFixedHeight(int fixedHeight) {
        this.fixedHeight = DensityUtil.dp2px(fixedHeight);
        dialog.setFixedHeight(this.fixedHeight);
    }

    /**
     * 设置dialog的宽度占屏幕宽度的百分比。
     */
    public void setWidthPercent(float widthPercent) {
        this.widthPercent = widthPercent;
        dialog.setWidthPercent(widthPercent);
    }

    /**
     * 设置dialog的宽度 单位dp。
     */
    public void setFixedWidth(int fixedWidth) {
        this.fixedWidth = DensityUtil.dp2px(fixedWidth);
        dialog.setFixedWidth(this.fixedWidth);
    }

    /**
     * 当gravity = Gravity.LEFT ,Gravity.RIGHT, Gravity.START, Gravity.END, Gravity.CENTER_HORIZONTAL时有效
     */
    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = DensityUtil.dp2px(horizontalMargin);
        dialog.setHorizontalMargin(this.horizontalMargin);
    }

    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = DensityUtil.dp2px(verticalMargin);
        dialog.setVerticalMargin(this.verticalMargin);
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        dialog.setGravity(gravity);
    }


    public void setCanceledOnTouchOutside(boolean boo) {
        dialog.setCanceledOnTouchOutside(boo);
    }

    public void setCanHideWhenSwipeDown(boolean boo) {
        dialog.setCancelable(boo);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }

    public Context getContext() {
        return context;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public MyOutLine getMyOutLine() {
        return myOutLine;
    }

    public float getHeightPercent() {
        return heightPercent;
    }

    public float getWidthPercent() {
        return widthPercent;
    }

    public int getFixedHeight() {
        return fixedHeight;
    }

    public int getFixedWidth() {
        return fixedWidth;
    }

    public int getHorizontalMargin() {
        return horizontalMargin;
    }

    public int getVerticalMargin() {
        return verticalMargin;
    }

    public int getGravity() {
        return gravity;
    }
}
