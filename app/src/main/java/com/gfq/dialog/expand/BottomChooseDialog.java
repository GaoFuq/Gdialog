package com.gfq.dialog.expand;

import android.content.Context;
import android.graphics.Color;


import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomRoundDialog;
import com.gfq.dialog.databinding.BottomChooseDialogBinding;

import java.util.List;

import androidx.databinding.ViewDataBinding;

/**
 * create by 高富强
 * on {2019/10/17} {15:26}
 * desctapion:
 */
public abstract class BottomChooseDialog<T> {
    private List<T> dataList;
    private T content;
    private BaseBottomRoundDialog<BottomChooseDialogBinding> dialog;
    private BottomChooseDialogBinding binding;

    public BottomChooseDialog(Context context) {
        dialog = new BaseBottomRoundDialog<BottomChooseDialogBinding>(context) {
            @Override
            protected int layout() {
                return R.layout.bottom_choose_dialog;
            }

            @Override
            protected void bindView(BottomChooseDialogBinding dialogBinding) {
                binding = dialogBinding;
                init();
            }
        };
    }


    private void init() {
        binding.tvTitle.setText(getTitle());
        binding.tvCancel.setOnClickListener(v -> dialog.dismiss());
        binding.tvConfirm.setOnClickListener(v -> {
            dialog.dismiss();
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
        binding.wheelView.setAdapter(adapter);
        binding.wheelView.setCurrentItem(0);
        binding.wheelView.setCyclic(false);
        binding.wheelView.setOnItemSelectedListener(index -> content = dataList.get(index));
        binding.wheelView.setTextColorCenter(Color.parseColor("#333333"));
        binding.wheelView.setTextColorOut(Color.parseColor("#666666"));
        binding.wheelView.setTextSize(14);
        binding.wheelView.setDividerColor(Color.parseColor("#cccccc"));
        binding.wheelView.setLineSpacingMultiplier(3);
        binding.wheelView.setDividerType(WheelView.DividerType.WRAP);
    }

    public void setTitleStyle(String text, int textColor, int textSize) {
        binding.tvTitle.setText(text);
        binding.tvTitle.setTextColor(textColor);
        binding.tvTitle.setTextSize(textSize);
    }

    public void setConfirmStyle(String text, int textColor, int textSize) {
        binding.tvConfirm.setText(text);
        binding.tvConfirm.setTextColor(textColor);
        binding.tvConfirm.setTextSize(textSize);
    }

    public void setCancelStyle(String text, int textColor, int textSize) {
        binding.tvCancel.setText(text);
        binding.tvCancel.setTextColor(textColor);
        binding.tvCancel.setTextSize(textSize);
    }

    protected abstract String getTitle();

    public abstract List<T> getDataList();

    protected abstract void onConfirmClicked(T content);


}
