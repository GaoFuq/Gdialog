package com.gfq.dialog.expand.choosedate;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomRoundDialog;
import com.gfq.dialog.base.BaseRoundDialog;
import com.gfq.dialog.base.DialogType;
import com.gfq.dialog.base.GDialog;
import com.gfq.dialog.databinding.DialogChooseDateBinding;
import com.gfq.dialog.util.DensityUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChooseDateDialog {
    private Context context;
    private DialogType dialogType;
    private DateType dateType;

    private int wvTextColor = Color.parseColor("#999999");
    private int wvTextColorCenter = Color.parseColor("#333333");
    private int wvTextSize = 16;

    public ChooseDateDialog(Context context, DialogType dialogType, DateType dateType) {
        this.context = context;
        this.dialogType = dialogType;
        this.dateType = dateType;
        initDateDialogData();
        initChooseDateDialog();
    }

    private GDialog<DialogChooseDateBinding> chooseDateDialog;
    private DialogChooseDateBinding binding;
    private WheelAdapter<Integer> yearAdapter;
    private WheelAdapter<Integer> monthAdapter;
    private WheelAdapter<Integer> dayAdapter;
    private ArrayList<Integer> yearList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> dayList;
    private String year = "";
    private String month = "";
    private String day = "";

    private void initDateDialogData() {
        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i < year + 3; i++) {
            yearList.add(i);
        }
        for (int i = 1; i < 13; i++) {
            monthList.add(i);
        }
        for (int i = 1; i < 32; i++) {
            dayList.add(i);
        }
        yearAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return yearList.size();
            }

            @Override
            public Integer getItem(int index) {
                return yearList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return yearList.indexOf(o);
            }
        };
        monthAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return monthList.size();
            }

            @Override
            public Integer getItem(int index) {
                return monthList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return monthList.indexOf(o);
            }
        };
        dayAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return dayList.size();
            }

            @Override
            public Integer getItem(int index) {
                return dayList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return dayList.indexOf(o);
            }
        };
    }

    private void initChooseDateDialog() {
        if (context != null) {
            if (chooseDateDialog == null) {
                if (dialogType == DialogType.normal) {
                    chooseDateDialog = new BaseRoundDialog<>(context);
                } else if (dialogType == DialogType.bottom) {
                    chooseDateDialog = new BaseBottomRoundDialog<>(context);
                }

                assert chooseDateDialog != null;
                binding = chooseDateDialog.bindView(R.layout.dialog_choose_date);

                WheelView wvYear = binding.wvYear;
                WheelView wvMonth = binding.wvMonth;
                WheelView wvDay = binding.wvDay;
                //默认-年月日-都有
                if (dateType == DateType.year_month) {
                    wvMonth.setVisibility(View.GONE);
                } else if (dateType == DateType.month_day) {
                    wvDay.setVisibility(View.GONE);
                }
                wvYear.setAdapter(yearAdapter);
                wvMonth.setAdapter(monthAdapter);
                wvDay.setAdapter(dayAdapter);
                wvYear.setCyclic(false);
                wvMonth.setCyclic(false);
                wvYear.setCurrentItem(0);

                int m = Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH);
                wvMonth.setCurrentItem(m);
                int d = Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH);

                wvDay.setCurrentItem(d - 1);
                setWheelViewDefStyle(wvTextColor,wvTextColorCenter,wvTextSize);

                wvYear.setOnItemSelectedListener(index -> year = (int) wvYear.getAdapter().getItem(index) + "");
                wvMonth.setOnItemSelectedListener(index -> month = (int) wvMonth.getAdapter().getItem(index) + "");
                wvDay.setOnItemSelectedListener(index -> day = (int) wvDay.getAdapter().getItem(index) + "");

                binding.tvCancel.setOnClickListener(v -> chooseDateDialog.dismiss());
                binding.tvConfirm.setOnClickListener(v -> {
                    chooseDateDialog.dismiss();
                    if (year.equals("")) {
                        year = yearAdapter.getItem(0) + "";
                    }
                    if (month.equals("")) {
                        month = monthAdapter.getItem(m) + "";
                    }
                    if (day.equals("")) {
                        day = dayAdapter.getItem(d - 1) + "";
                    }
                    if (month.length() == 1 && Integer.parseInt(month) < 10) {
                        month = "0" + month;
                    }
                    if (day.length() == 1 && Integer.parseInt(day) < 10) {
                        day = "0" + day;
                    }
                    if (onChooseDateConfirmListener != null) {
                        onChooseDateConfirmListener.onConfirm(year, month, day);
                    }
                });
            }
        }
    }

    private void setWheelViewDefStyle(int textColor, int textColorCenter, int textSize) {
        binding.wvYear.setTextColorOut(textColor);
        binding.wvYear.setTextColorCenter(textColorCenter);
        binding.wvYear.setTextSize(textSize);
        binding.wvYear.setLabel("年");

        binding.wvMonth.setTextColorOut(textColor);
        binding.wvMonth.setTextColorCenter(textColorCenter);
        binding.wvMonth.setTextSize(textSize);
        binding.wvMonth.setLabel("月");

        binding.wvDay.setTextColorOut(textColor);
        binding.wvDay.setTextColorCenter(textColorCenter);
        binding.wvDay.setTextSize(textSize);
        binding.wvDay.setLabel("日");
    }

    //是否只显示中间的Label
    public void isCenterLabel(boolean b){
        binding.wvYear.isCenterLabel(b);
        binding.wvMonth.isCenterLabel(b);
        binding.wvDay.isCenterLabel(b);
    }



    public void setWheelViewStyle(int textColor, int textColorCenter, int textSize) {
        setWheelViewDefStyle(textColor,textColorCenter,textSize);
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
    public void setLineHeight(float lineHeight){
        binding.wvYear.setLineSpacingMultiplier(lineHeight);
        binding.wvMonth.setLineSpacingMultiplier(lineHeight);
        binding.wvDay.setLineSpacingMultiplier(lineHeight);

    }
    public void show() {
        if (chooseDateDialog != null) {
            chooseDateDialog.show();
        }
    }

    public interface OnChooseDateConfirmListener {
        void onConfirm(String year, String month, String day);
    }

    private OnChooseDateConfirmListener onChooseDateConfirmListener;

    public void setOnChooseDateConfirmListener(OnChooseDateConfirmListener onChooseDateConfirmListener) {
        this.onChooseDateConfirmListener = onChooseDateConfirmListener;
    }
}
