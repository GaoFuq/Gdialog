package com.gfq.dialog.expand;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomRoundDialog;
import com.gfq.dialog.databinding.BottomChooseDialogBinding;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/17} {15:26}
 * desctapion:
 */
public abstract class BottomChooseDialog<T> extends BaseBottomRoundDialog<BottomChooseDialogBinding> {
    private List<T> dataList;
    private T content;

    public BottomChooseDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        dialogBinding = bindView(R.layout.bottom_choose_dialog);
        dialogBinding.tvTitle.setText(getTitle());
        dialogBinding.tvCancel.setOnClickListener(v -> dismiss());
        dialogBinding.tvConfirm.setOnClickListener(v -> {
            dismiss();
            onConfirmClicked(content);
        });

        dataList = getDataList();
        content = dataList.get(1);
        WheelAdapter<T> adapter = new WheelAdapter<T>() {
            @Override
            public int getItemsCount() {
                return dataList == null ? 0 : dataList.size();
            }

            @Override
            public T getItem(int index) {
                return dataList.get(index);
            }

            @Override
            public int indexOf(T o) {
                return dataList.indexOf(o);
            }
        };
        dialogBinding.wheelView.setAdapter(adapter);
        dialogBinding.wheelView.setCurrentItem(0);
        dialogBinding.wheelView.setCyclic(false);
        dialogBinding.wheelView.setOnItemSelectedListener(index -> content = dataList.get(index));
        dialogBinding.wheelView.setTextColorCenter(Color.parseColor("#333333"));
        dialogBinding.wheelView.setTextColorOut(Color.parseColor("#666666"));
        dialogBinding.wheelView.setTextSize(14);
        dialogBinding.wheelView.setDividerColor(Color.parseColor("#cccccc"));
        dialogBinding.wheelView.setLineSpacingMultiplier(3);
        dialogBinding.wheelView.setDividerType(WheelView.DividerType.WRAP);
    }

    public void setTitleStyle(String text, int textColor, int textSize) {
        dialogBinding.tvTitle.setText(text);
        dialogBinding.tvTitle.setTextColor(textColor);
        dialogBinding.tvTitle.setTextSize(textSize);
    }

    public void setConfirmStyle(String text, int textColor, int textSize) {
        dialogBinding.tvConfirm.setText(text);
        dialogBinding.tvConfirm.setTextColor(textColor);
        dialogBinding.tvConfirm.setTextSize(textSize);
    }

    public void setCancelStyle(String text, int textColor, int textSize) {
        dialogBinding.tvCancel.setText(text);
        dialogBinding.tvCancel.setTextColor(textColor);
        dialogBinding.tvCancel.setTextSize(textSize);
    }

    protected abstract String getTitle();

    public abstract List<T> getDataList();

    protected abstract void onConfirmClicked(T content);


}
