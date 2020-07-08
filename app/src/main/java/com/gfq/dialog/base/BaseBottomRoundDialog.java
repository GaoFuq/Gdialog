package com.gfq.dialog.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gfq.dialog.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * create by 高富强
 * on {2019/10/17} {10:05}
 * desctapion:
 */
public class BaseBottomRoundDialog<T extends ViewDataBinding> extends FrameLayout  implements GDialog<T>{
    private final LayoutInflater layoutInflater;
    protected T dialogBinding;
    private BottomSheetDialog dialog;
    protected Context context;
    private FrameLayout container;

    public BaseBottomRoundDialog(Context context) {
        super(context);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        initBase();

    }


    private void initBase() {
        View view = LayoutInflater.from(context).inflate(R.layout.base_bottom_round_dialog, this, false);
        container = view.findViewById(R.id.container);
        dialog = new BottomSheetDialog(context);
        dialog.setContentView(view);
        dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        dialog.setCancelable(false);
    }

    @Override
    public T bindView(@LayoutRes int layout) {
        dialogBinding = DataBindingUtil.inflate(layoutInflater, layout, null, false);
        container.addView(dialogBinding.getRoot());
        return dialogBinding;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setCanceledOnTouchOutside(boolean boo) {
        dialog.setCanceledOnTouchOutside(boo);
    }

}
