package com.gfq.dialog.base;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

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
public abstract class BaseBottomDialog<T extends ViewDataBinding> {
    private final LayoutInflater layoutInflater;
    protected T dgBinding;
    private BottomSheetDialog dialog;
    protected final Context context;

    public BaseBottomDialog(@NotNull Context context) {
        this(context,0);
    }

    /**
     * 固定高度
     * @param heightPercent  占屏幕高度的比例
     */
    public BaseBottomDialog(@NotNull Context context, float heightPercent) {
        dialog = new MyBottomSheetDialog(context, heightPercent);
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        initBase();
    }

    /**
     * 固定高度
     * @param fixedHeight  固定值 单位dp
     */
    public BaseBottomDialog(@NotNull Context context, int fixedHeight) {
        dialog = new MyBottomSheetDialog(context, fixedHeight);
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        initBase();
    }

    private void initBase() {
        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        dialog.setContentView(dgBinding.getRoot());
        View parent = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if(parent !=null){
            parent.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        dialog.setCanceledOnTouchOutside(true);
        bindView();
    }


    public BottomSheetDialog getDialog() {
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


}
