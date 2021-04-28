package com.gfq.dialog.expand;

import android.content.Context;
import android.graphics.Color;


import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomDialog;
import com.gfq.dialog.databinding.BottomChooseDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * create by 高富强
 * on {2019/10/17} {15:26}
 * desctapion:
 */
public abstract class BottomChooseDialog extends BaseBottomDialog<BottomChooseDialogBinding> {

    private String content;
    private List<String> dataList;
    private int index = 0;


    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        WheelAdapter<String> adapter = new WheelAdapter<String>() {
            @Override
            public int getItemsCount() {
                return dataList == null ? 0 : dataList.size();
            }

            @Override
            public String getItem(int index) {
                return dataList.get(index);
            }

            @Override
            public int indexOf(String o) {
                return dataList.indexOf(o);
            }
        };

        content = dataList.get(0);
        dgBinding.wheelView.setAdapter(adapter);
        dgBinding.wheelView.setCurrentItem(0);
        dgBinding.wheelView.setCyclic(false);
        dgBinding.wheelView.setOnItemSelectedListener(index -> {
            content = dataList.get(index);
            this.index=index;
        });
        dgBinding.wheelView.setTextColorCenter(Color.parseColor("#333333"));
        dgBinding.wheelView.setTextColorOut(Color.parseColor("#666666"));
        dgBinding.wheelView.setTextSize(14);
        dgBinding.wheelView.setDividerColor(Color.parseColor("#cccccc"));
        dgBinding.wheelView.setLineSpacingMultiplier(3);
        dgBinding.wheelView.setDividerType(WheelView.DividerType.WRAP);
    }

    public BottomChooseDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.bottom_choose_dialog;
    }

    @Override
    protected void bindView() {
        init();
    }

    private void init() {
        dgBinding.tvTitle.setText(title());
        dgBinding.tvCancel.setOnClickListener(v -> dismiss());
        dgBinding.tvConfirm.setOnClickListener(v -> {
            dismiss();
            onConfirmClicked(content,index);
        });
    }




    public void setTitleStyle(String text, int textColor, int textSize) {
        dgBinding.tvTitle.setText(text);
        dgBinding.tvTitle.setTextColor(textColor);
        dgBinding.tvTitle.setTextSize(textSize);
    }

    public void setConfirmStyle(String text, int textColor, int textSize) {
        dgBinding.tvConfirm.setText(text);
        dgBinding.tvConfirm.setTextColor(textColor);
        dgBinding.tvConfirm.setTextSize(textSize);
    }

    public void setCancelStyle(String text, int textColor, int textSize) {
        dgBinding.tvCancel.setText(text);
        dgBinding.tvCancel.setTextColor(textColor);
        dgBinding.tvCancel.setTextSize(textSize);
    }

    protected abstract String title();

    protected abstract void onConfirmClicked(String content, int index);


}
