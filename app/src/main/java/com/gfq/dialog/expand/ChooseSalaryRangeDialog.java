package com.gfq.dialog.expand;

import android.content.Context;
import android.graphics.Color;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomRoundDialog;
import com.gfq.dialog.base.BaseRoundDialog;
import com.gfq.dialog.base.DialogType;
import com.gfq.dialog.base.GDialog;
import com.gfq.dialog.databinding.DialogChooseSalaryRangeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @created GaoFuq
 * @Date 2020/7/22 16:32
 * @Descaption
 */
public class ChooseSalaryRangeDialog {

    private Context context;
    private DialogType dialogType;
    private int minSalary = 1;
    private int maxSalary = 50;
    private String label = "k";//单位
    private int wvTextColor = Color.parseColor("#999999");
    private int wvTextColorCenter = Color.parseColor("#333333");
    private int wvTextSize = 16;
    private GDialog dialog;
    private DialogChooseSalaryRangeBinding binding;
    private WheelAdapter<String> wvMinAdapter;
    private WheelAdapter<String> wvMaxAdapter;
    private ArrayList<Integer> minSalaryList;
    private ArrayList<Integer> maxSalaryList;
    private int selectedMinSalary;
    private int selectedMaxSalary;


    public ChooseSalaryRangeDialog(Context context, DialogType dialogType) {
        this.context = context;
        this.dialogType = dialogType;
        initData();
        initDialog();
    }

    public ChooseSalaryRangeDialog(Context context, DialogType dialogType, int minSalary, int maxSalary, String label) {
        this.context = context;
        this.dialogType = dialogType;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.label = label;
        initData();
        initDialog();
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


    private void initDialog() {
        if (context != null) {
            if (dialog == null) {
                if (dialogType == DialogType.normal) {
                    dialog = new BaseRoundDialog<DialogChooseSalaryRangeBinding>(context) {
                        @Override
                        protected int layout() {
                            return R.layout.dialog_choose_salary_range;
                        }

                        @Override
                        protected void bindView(DialogChooseSalaryRangeBinding dialogBinding) {
                            bindDialogView(dialogBinding);
                        }
                    };
                } else if (dialogType == DialogType.bottom) {
                    dialog = new BaseBottomRoundDialog<DialogChooseSalaryRangeBinding>(context) {
                        @Override
                        protected int layout() {
                            return R.layout.dialog_choose_salary_range;
                        }

                        @Override
                        protected void bindView(DialogChooseSalaryRangeBinding dialogBinding) {
                                bindDialogView(dialogBinding);
                        }
                    };
                }


            }
        }
    }

    private void bindDialogView(DialogChooseSalaryRangeBinding dialogBinding) {

        binding.wvMin.setAdapter(wvMinAdapter);
        binding.wvMax.setAdapter(wvMaxAdapter);

        binding.wvMin.setCyclic(false);
        binding.wvMax.setCyclic(false);
        binding.wvMin.setCurrentItem(0);
        binding.wvMax.setCurrentItem(0);

        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize);
        setLineHeight(3);
        isCenterLabel(false);

        binding.wvMin.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectedMinSalary = minSalaryList.get(index);
                maxSalaryList.clear();
                if (selectedMinSalary == maxSalary) {
                    maxSalaryList.add(selectedMinSalary);
                    binding.wvMax.setAdapter(new SalaryAdapter(maxSalaryList));
                    return;
                }
                if(selectedMinSalary>=selectedMaxSalary)
                    for (int i = selectedMinSalary + 1; i < maxSalary + 1; i++) {
                        maxSalaryList.add(i);
                    }
                binding.wvMax.setAdapter(new SalaryAdapter(maxSalaryList));
                binding.wvMax.setCurrentItem(0);

            }
        });
        binding.wvMax.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectedMaxSalary = maxSalaryList.get(index);
            }
        });

        binding.tvConfirm.setOnClickListener(v -> {
            if (onConfirmListener != null) {
                onConfirmListener.onConfirm(selectedMinSalary + label, selectedMaxSalary + label);
            }
            dialog.dismiss();
        });

        binding.tvCancel.setOnClickListener(v -> dialog.dismiss());
    }


    private void setWheelViewDefStyle(int textColor, int textColorCenter, int textSize) {
        binding.wvMin.setTextColorOut(textColor);
        binding.wvMin.setTextColorCenter(textColorCenter);
        binding.wvMin.setTextSize(textSize);
        binding.wvMin.setLabel(label);

        binding.wvMax.setTextColorOut(textColor);
        binding.wvMax.setTextColorCenter(textColorCenter);
        binding.wvMax.setTextSize(textSize);
        binding.wvMax.setLabel(label);
    }

    //是否只显示中间的Label  默认为true
    public void isCenterLabel(boolean b) {
        binding.wvMin.isCenterLabel(b);
        binding.wvMax.isCenterLabel(b);
    }

    public void setWheelViewStyle(int textColor, int textColorCenter, int textSize) {
        setWheelViewDefStyle(textColor, textColorCenter, textSize);
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

    public void setLineHeight(float lineHeight) {
        binding.wvMin.setLineSpacingMultiplier(lineHeight);
        binding.wvMax.setLineSpacingMultiplier(lineHeight);
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }


    public void dismiss(){
        dialog.dismiss();
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
}
