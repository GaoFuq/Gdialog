package com.gfq.dialog.expand.choosedate;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.contrarywind.adapter.WheelAdapter;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomDialog;
import com.gfq.dialog.databinding.DialogChooseDateBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChooseDateBottomDialog extends BaseBottomDialog<DialogChooseDateBinding> {
    private WheelAdapter<Integer> yearAdapter;
    private WheelAdapter<Integer> monthAdapter;
    private WheelAdapter<Integer> dayAdapter;
    private ArrayList<Integer> yearList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> dayList;
    private String year = "";
    private String month = "";
    private String day = "";
    private int wvTextColor = Color.parseColor("#999999");
    private int wvTextColorCenter = Color.parseColor("#333333");
    private int wvTextSize = 16;

    public ChooseDateBottomDialog(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.dialog_choose_date;
    }



    @Override
    protected void bindView() {
        setCanHideWhenSwipeDown(false);
        setCanceledOnTouchOutside(true);
        initData();
        setDateType(DateType.year_month_day);
        bindDialogView();
    }


    public void setDateType(DateType dateType) {
        dgBinding.llYear.setVisibility(View.VISIBLE);
        dgBinding.llDay.setVisibility(View.VISIBLE);

        if (dateType == DateType.year_month) {
            dgBinding.llDay.setVisibility(View.GONE);
        } else if (dateType == DateType.month_day) {
            dgBinding.llYear.setVisibility(View.GONE);
        }
    }


    private void initData() {
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


    private void bindDialogView() {

        //默认-年月日-都有

        dgBinding.wvYear.setAdapter(yearAdapter);
        dgBinding.wvMonth.setAdapter(monthAdapter);
        dgBinding.wvDay.setAdapter(dayAdapter);

        dgBinding.wvYear.setCyclic(false);
        dgBinding.wvMonth.setCyclic(false);
        dgBinding.wvYear.setCurrentItem(0);

        int m = Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH);
        dgBinding.wvMonth.setCurrentItem(m);
        int d = Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH);

        dgBinding.wvDay.setCurrentItem(d - 1);

        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize);
        setLineHeight(3);
        isCenterLabel(false);


        dgBinding.wvYear.setOnItemSelectedListener(index -> year = (int) dgBinding.wvYear.getAdapter().getItem(index) + "");
        dgBinding.wvMonth.setOnItemSelectedListener(index -> month = (int) dgBinding.wvMonth.getAdapter().getItem(index) + "");
        dgBinding.wvDay.setOnItemSelectedListener(index -> day = (int) dgBinding.wvDay.getAdapter().getItem(index) + "");

        dgBinding.tvCancel.setOnClickListener(v -> dismiss());
        dgBinding.tvConfirm.setOnClickListener(v -> {
            dismiss();
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

    private void setWheelViewDefStyle(int textColor, int textColorCenter, int textSize) {
        dgBinding.wvYear.setTextColorOut(textColor);
        dgBinding.wvYear.setTextColorCenter(textColorCenter);
        dgBinding.wvYear.setTextSize(textSize);
        dgBinding.wvYear.setLabel("年");

        dgBinding.wvMonth.setTextColorOut(textColor);
        dgBinding.wvMonth.setTextColorCenter(textColorCenter);
        dgBinding.wvMonth.setTextSize(textSize);
        dgBinding.wvMonth.setLabel("月");

        dgBinding.wvDay.setTextColorOut(textColor);
        dgBinding.wvDay.setTextColorCenter(textColorCenter);
        dgBinding.wvDay.setTextSize(textSize);
        dgBinding.wvDay.setLabel("日");
    }

    //是否只显示中间的Label  默认为true
    public void isCenterLabel(boolean b) {
        dgBinding.wvYear.isCenterLabel(b);
        dgBinding.wvMonth.isCenterLabel(b);
        dgBinding.wvDay.isCenterLabel(b);
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
        dgBinding.wvYear.setLineSpacingMultiplier(lineHeight);
        dgBinding.wvMonth.setLineSpacingMultiplier(lineHeight);
        dgBinding.wvDay.setLineSpacingMultiplier(lineHeight);

    }


    public interface OnChooseDateConfirmListener {
        void onConfirm(String year, String month, String day);
    }

    private OnChooseDateConfirmListener onChooseDateConfirmListener;

    public void setOnChooseDateConfirmListener(OnChooseDateConfirmListener onChooseDateConfirmListener) {
        this.onChooseDateConfirmListener = onChooseDateConfirmListener;
    }
}
