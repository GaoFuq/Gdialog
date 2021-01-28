package com.gfq.dialog.expand;

import android.content.Context;
import android.graphics.Color;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomRoundDialog;
import com.gfq.dialog.base.BaseRoundDialog;
import com.gfq.dialog.databinding.DialogChooseSalaryRangeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @created GaoFuq
 * @Date 2020/7/22 16:32
 * @Descaption
 */
public class ChooseSalaryRangeBottomDialog extends BaseBottomRoundDialog<DialogChooseSalaryRangeBinding> {

    private Context context;
    private int minSalary = 1;
    private int maxSalary = 50;
    private String label = "k";//单位
    private int wvTextColor = Color.parseColor("#999999");
    private int wvTextColorCenter = Color.parseColor("#333333");
    private int wvTextSize = 16;
    private WheelAdapter<String> wvMinAdapter;
    private WheelAdapter<String> wvMaxAdapter;
    private ArrayList<Integer> minSalaryList;
    private ArrayList<Integer> maxSalaryList;
    private int selectedMinSalary;
    private int selectedMaxSalary;


    public ChooseSalaryRangeBottomDialog(Context context) {
        super(context);
    }
    public ChooseSalaryRangeBottomDialog(Context context, int minSalary, int maxSalary, String label) {
        super(context);
        this.context = context;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.label = label;
    }

    @Override
    protected int layout() {
        return R.layout.dialog_choose_salary_range;
    }

    @Override
    protected void bindView() {
        initData();
        bindDialogView();
    }


    private void initData() {
        if (minSalaryList == null) {
            minSalaryList = new ArrayList<>();
        }
        if (maxSalaryList == null) {
            maxSalaryList = new ArrayList<>();
        }

        for (int i = minSalary; i < maxSalary + 1; i++) {
            minSalaryList.add(i);
        }

        for (int i = minSalary + 1; i < maxSalary + 1; i++) {
            maxSalaryList.add(i);
        }

        wvMinAdapter = new SalaryAdapter(minSalaryList);
        wvMaxAdapter = new SalaryAdapter(maxSalaryList);
    }




    private void bindDialogView() {

        dgBinding.wvMin.setAdapter(wvMinAdapter);
        dgBinding.wvMax.setAdapter(wvMaxAdapter);

        dgBinding.wvMin.setCyclic(false);
        dgBinding.wvMax.setCyclic(false);
        dgBinding.wvMin.setCurrentItem(0);
        dgBinding.wvMax.setCurrentItem(0);

        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize);
        setLineHeight(3);
        isCenterLabel(false);

        dgBinding.wvMin.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectedMinSalary = minSalaryList.get(index);
                maxSalaryList.clear();
                if (selectedMinSalary == maxSalary) {
                    maxSalaryList.add(selectedMinSalary);
                    dgBinding.wvMax.setAdapter(new SalaryAdapter(maxSalaryList));
                    return;
                }
                if(selectedMinSalary>=selectedMaxSalary)
                    for (int i = selectedMinSalary + 1; i < maxSalary + 1; i++) {
                        maxSalaryList.add(i);
                    }
                dgBinding.wvMax.setAdapter(new SalaryAdapter(maxSalaryList));
                dgBinding.wvMax.setCurrentItem(0);

            }
        });
        dgBinding.wvMax.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectedMaxSalary = maxSalaryList.get(index);
            }
        });

        dgBinding.tvConfirm.setOnClickListener(v -> {
            if (onConfirmListener != null) {
                onConfirmListener.onConfirm(selectedMinSalary + label, selectedMaxSalary + label);
            }
            dismiss();
        });

        dgBinding.tvCancel.setOnClickListener(v -> dismiss());
    }


    private void setWheelViewDefStyle(int textColor, int textColorCenter, int textSize) {
        dgBinding.wvMin.setTextColorOut(textColor);
        dgBinding.wvMin.setTextColorCenter(textColorCenter);
        dgBinding.wvMin.setTextSize(textSize);
        dgBinding.wvMin.setLabel(label);

        dgBinding.wvMax.setTextColorOut(textColor);
        dgBinding.wvMax.setTextColorCenter(textColorCenter);
        dgBinding.wvMax.setTextSize(textSize);
        dgBinding.wvMax.setLabel(label);
    }

    //是否只显示中间的Label  默认为true
    public void isCenterLabel(boolean b) {
        dgBinding.wvMin.isCenterLabel(b);
        dgBinding.wvMax.isCenterLabel(b);
    }

    public void setWheelViewStyle(int textColor, int textColorCenter, int textSize) {
        setWheelViewDefStyle(textColor, textColorCenter, textSize);
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

    public void setLineHeight(float lineHeight) {
        dgBinding.wvMin.setLineSpacingMultiplier(lineHeight);
        dgBinding.wvMax.setLineSpacingMultiplier(lineHeight);
    }






    public interface OnConfirmListener {
        void onConfirm(String minSalary, String maxSalary);
    }

    private OnConfirmListener onConfirmListener;

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    static class SalaryAdapter implements WheelAdapter<String> {

        private List<Integer> list;

        public SalaryAdapter(List<Integer> list) {
            this.list = list;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        public String getItem(int index) {
            return list.get(index) + "";
        }

        @Override
        public int indexOf(String o) {
            return 0;
        }
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getWvTextColor() {
        return wvTextColor;
    }

    public void setWvTextColor(int wvTextColor) {
        this.wvTextColor = wvTextColor;
    }

    public int getWvTextColorCenter() {
        return wvTextColorCenter;
    }

    public void setWvTextColorCenter(int wvTextColorCenter) {
        this.wvTextColorCenter = wvTextColorCenter;
    }

    public int getWvTextSize() {
        return wvTextSize;
    }

    public void setWvTextSize(int wvTextSize) {
        this.wvTextSize = wvTextSize;
    }

}
