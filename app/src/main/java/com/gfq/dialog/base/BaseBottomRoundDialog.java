package com.gfq.dialog.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.gfq.dialog.R;
import com.gfq.dialog.util.DensityUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.jetbrains.annotations.NotNull;

/**
 * create by 高富强
 * on {2019/10/17} {10:05}
 * desctapion:
 */
public abstract class BaseBottomRoundDialog<T extends ViewDataBinding> {
    private final LayoutInflater layoutInflater;
    protected T dgBinding;
    private BottomSheetDialog dialog;
    protected final Context context;
    private int radius = DensityUtil.dp2px(10f);//dp

    public BaseBottomRoundDialog(@NotNull Context context) {
       this(context, DensityUtil.dp2px(10f));
    }
    public BaseBottomRoundDialog(@NotNull Context context,int radius) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.radius=radius;
        initBase();
    }



    public BottomSheetDialog getDialog() {
        return dialog;
    }

    private void initBase() {
        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(dgBinding.getRoot());
//        dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackground(getDrawable());
        dialog.setCanceledOnTouchOutside(true);
        beforeBindView();
        bindView();
    }

    protected void beforeBindView() {

    }

    private Drawable getDrawable() {
        float[] outerR = new float[] { radius, radius, radius, radius, 0, 0, 0, 0 };
        RoundRectShape roundRectShape = new RoundRectShape(outerR, null, null);
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        drawable.setTint(Color.WHITE);
        return drawable;
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

    public void setCanceledOnTouchOutside(boolean boo) {
        dialog.setCanceledOnTouchOutside(boo);
    }

    public void setCanHideWhenSwipeDown(boolean boo) {
        dialog.setCancelable(boo);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }

}
