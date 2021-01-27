package com.gfq.dialog.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.gfq.dialog.R;
import com.gfq.dialog.util.DensityUtil;

import androidx.annotation.LayoutRes;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


/**
 * create by 高富强
 * on {2019/10/15} {17:22}
 * desctapion:
 */
public abstract class BaseRoundDialog<T extends ViewDataBinding> implements GDialog{
    private Context context;
    private AlertDialog dialog;
    private LayoutInflater layoutInflater;
    protected T dgBinding;

    public BaseRoundDialog(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        init();
    }



    private void init() {
        dgBinding = DataBindingUtil.inflate(layoutInflater, layout(), null, false);
        dialog = new AlertDialog.Builder(context).create();
        dialog.setView(dgBinding.getRoot());
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
//        dialog.setView(dgBinding.getRoot(), marginLeft, marginTop, marginRight, marginBottom);

        bindView();
    }

    protected abstract @LayoutRes int layout();

    protected abstract void bindView();



    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public AlertDialog getAlert() {
        return dialog;
    }

    public void setCanceledOnTouchOutside(boolean boo) {
        dialog.setCanceledOnTouchOutside(boo);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener){
        dialog.setOnDismissListener(listener);
    }


}


