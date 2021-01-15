package com.gfq.dialog.base;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.gfq.dialog.R;
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
public abstract class BaseBottomRoundDialog<T extends ViewDataBinding> implements GDialog {
    private final LayoutInflater layoutInflater;
    protected T dgBinding;
    private BottomSheetDialog dialog;
    protected final Context context;
    private FrameLayout container;

    public BaseBottomRoundDialog(@NotNull Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        initBase();
    }


    public FrameLayout getContainer() {
        return container;
    }

    public BottomSheetDialog getDialog() {
        return dialog;
    }

    private void initBase() {
        View view = LayoutInflater.from(context).inflate(R.layout.base_bottom_round_dialog, null);

        container = view.findViewById(R.id.container);
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        dialog.setCanceledOnTouchOutside(true);

        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        container.addView(dgBinding.getRoot());
        bindView();

    }


    protected abstract @LayoutRes
    int layout();

    protected abstract void bindView();

    @Override
    public void show() {
        dialog.show();
    }

    @Override
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
