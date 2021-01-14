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

/**
 * create by 高富强
 * on {2019/10/17} {10:05}
 * desctapion:
 */
public abstract class BaseBottomRoundDialog<T extends ViewDataBinding> implements GDialog {
    private final LayoutInflater layoutInflater;
    protected T dialogBinding;
    private BottomSheetDialog dialog;
    protected Context context;
    private FrameLayout container;

    public BaseBottomRoundDialog(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        initBase();
    }


    private void initBase() {
        View view = LayoutInflater.from(context).inflate(R.layout.base_bottom_round_dialog, null);

        container = view.findViewById(R.id.container);
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        dialog.setCanceledOnTouchOutside(true);

        dialogBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        if (dialogBinding == null) {
            throw new RuntimeException("dialogBinding == null");
        } else {
            container.addView(dialogBinding.getRoot());
            bindView(dialogBinding);
        }
    }


    protected abstract @LayoutRes
    int layout();

    protected abstract void bindView(T dialogBinding);

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

    public void setCanHideWhenSwipDown(boolean boo) {
        dialog.setCancelable(boo);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
    }

    public T getDialogBinding() {
        return dialogBinding;
    }
}
