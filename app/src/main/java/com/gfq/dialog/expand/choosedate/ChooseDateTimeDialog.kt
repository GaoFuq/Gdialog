package com.gfq.dialog.expand.choosedate;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.contrarywind.adapter.WheelAdapter;
import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseDialog;
import com.gfq.dialog.databinding.DialogChooseDateBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChooseDateTimeDialog extends BaseDialog<DialogChooseDateBinding> {
    private WheelAdapter<Integer> yearAdapter;
    private WheelAdapter<Integer> monthAdapter;
    private WheelAdapter<Integer> dayAdapter;
    private WheelAdapter<Integer> hourAdapter;
    private WheelAdapter<Integer> minAdapter;
    private WheelAdapter<Integer> secAdapter;
    private ArrayList<Integer> yearList;
    private ArrayList<Integer> monthList;
    private ArrayList<Integer> dayList;
    private ArrayList<Integer> hourList;
    private ArrayList<Integer> minList;
    private ArrayList<Integer> secList;
    private String year = "";
    private String month = "";
    private String day = "";
    private String hour = "";
    private String min = "";
    private String sec = "";
    private int wvTextColor = Color.parseColor("#999999");
    private int wvTextColorCenter = Color.parseColor("#333333");
    private int wvTextSize = 16;

    public ChooseDateTimeDialog(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.dialog_choose_date;
    }


    @Override
    protected void bindView() {
        initData();
        setDateType(DateType.year_month_day);
        bindDialogView();
    }



    public void setDateType(DateType dateType) {
        if (dateType == DateType.year_month_day) {
            dgBinding.llYear.setVisibility(View.VISIBLE);
            dgBinding.llMonth.setVisibility(View.VISIBLE);
            dgBinding.llDay.setVisibility(View.VISIBLE);
            dgBinding.llHour.setVisibility(View.GONE);
            dgBinding.llMin.setVisibility(View.GONE);
            dgBinding.llSec.setVisibility(View.GONE);
        } else if (dateType == DateType.year_month) {
            dgBinding.llYear.setVisibility(View.VISIBLE);
            dgBinding.llMonth.setVisibility(View.VISIBLE);
            dgBinding.llDay.setVisibility(View.GONE);
            dgBinding.llHour.setVisibility(View.GONE);
            dgBinding.llMin.setVisibility(View.GONE);
            dgBinding.llSec.setVisibility(View.GONE);
        } else if (dateType == DateType.month_day) {
            dgBinding.llMonth.setVisibility(View.VISIBLE);
            dgBinding.llDay.setVisibility(View.VISIBLE);
            dgBinding.llYear.setVisibility(View.GONE);
            dgBinding.llHour.setVisibility(View.GONE);
            dgBinding.llMin.setVisibility(View.GONE);
            dgBinding.llSec.setVisibility(View.GONE);
        } else if (dateType == DateType.hour_min_second) {
            dgBinding.llHour.setVisibility(View.VISIBLE);
            dgBinding.llMin.setVisibility(View.VISIBLE);
            dgBinding.llSec.setVisibility(View.VISIBLE);
            dgBinding.llYear.setVisibility(View.GONE);
            dgBinding.llMonth.setVisibility(View.GONE);
            dgBinding.llDay.setVisibility(View.GONE);
        }else if (dateType == DateType.hour_min) {
            dgBinding.llHour.setVisibility(View.VISIBLE);
            dgBinding.llMin.setVisibility(View.VISIBLE);
            dgBinding.llSec.setVisibility(View.GONE);
            dgBinding.llYear.setVisibility(View.GONE);
            dgBinding.llMonth.setVisibility(View.GONE);
            dgBinding.llDay.setVisibility(View.GONE);
        } else if (dateType == DateType.min_second) {
            dgBinding.llMin.setVisibility(View.VISIBLE);
            dgBinding.llSec.setVisibility(View.VISIBLE);
            dgBinding.llHour.setVisibility(View.GONE);
            dgBinding.llYear.setVisibility(View.GONE);
            dgBinding.llMonth.setVisibility(View.GONE);
            dgBinding.llDay.setVisibility(View.GONE);
        }
    }

    private void initData() {
        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
        hourList = new ArrayList<>();
        minList = new ArrayList<>();
        secList = new ArrayList<>();
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
        for (int i = 0; i < 24; i++) {
            hourList.add(i);
        }
        for (int i = 0; i < 60; i++) {
            minList.add(i);
            secList.add(i);
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
        hourAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return hourList.size();
            }

            @Override
            public Integer getItem(int index) {
                return hourList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return hourList.indexOf(o);
            }
        };
        minAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return minList.size();
            }

            @Override
            public Integer getItem(int index) {
                return minList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return minList.indexOf(o);
            }
        };
        secAdapter = new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return secList.size();
            }

            @Override
            public Integer getItem(int index) {
                return secList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return secList.indexOf(o);
            }
        };
    }




    private void bindDialogView() {



        dgBinding.wvYear.setAdapter(yearAdapter);
        dgBinding.wvMonth.setAdapter(monthAdapter);
        dgBinding.wvDay.setAdapter(dayAdapter);
        dgBinding.wvHour.setAdapter(hourAdapter);
        dgBinding.wvMin.setAdapter(minAdapter);
        dgBinding.wvSec.setAdapter(secAdapter);

        dgBinding.wvYear.setCyclic(false);
        dgBinding.wvMonth.setCyclic(false);
        dgBinding.wvDay.setCyclic(false);
        dgBinding.wvHour.setCyclic(false);
        dgBinding.wvMin.setCyclic(false);
        dgBinding.wvSec.setCyclic(false);
        dgBinding.wvYear.setCurrentItem(0);

        int m = Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH);
        dgBinding.wvMonth.setCurrentItem(m);
        int d = Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH);
        dgBinding.wvDay.setCurrentItem(Math.max((d - 1), 0));
        int h = Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY);
        dgBinding.wvHour.setCurrentItem(Math.max((h - 1), 0));
        int minute = Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE);
        dgBinding.wvMin.setCurrentItem(Math.max((minute - 1), 0));
        int second = Calendar.getInstance(Locale.CHINA).get(Calendar.SECOND);
        dgBinding.wvSec.setCurrentItem(Math.max((second - 1), 0));
        setLineHeight(3);
        isCenterLabel(false);


        dgBinding.wvYear.setOnItemSelectedListener(index -> year = (int) dgBinding.wvYear.getAdapter().getItem(index) + "");
        dgBinding.wvMonth.setOnItemSelectedListener(index -> month = (int) dgBinding.wvMonth.getAdapter().getItem(index) + "");
        dgBinding.wvDay.setOnItemSelectedListener(index -> day = (int) dgBinding.wvDay.getAdapter().getItem(index) + "");
        dgBinding.wvHour.setOnItemSelectedListener(index -> hour = (int) dgBinding.wvHour.getAdapter().getItem(index) + "");
        dgBinding.wvMin.setOnItemSelectedListener(index -> min = (int) dgBinding.wvMin.getAdapter().getItem(index) + "");
        dgBinding.wvSec.setOnItemSelectedListener(index -> sec = (int) dgBinding.wvSec.getAdapter().getItem(index) + "");

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
                day = dayAdapter.getItem(Math.max((d - 1), 0)) + "";
            }
            if (hour.equals("")) {
                hour = hourAdapter.getItem(Math.max((h - 1), 0)) + "";
            }
            if (min.equals("")) {
                min = minAdapter.getItem(Math.max((minute - 1), 0)) + "";
            }
            if (sec.equals("")) {
                sec = secAdapter.getItem(Math.max((second - 1), 0)) + "";
            }
            if (month.length() == 1 && Integer.parseInt(month) < 10) {
                month = "0" + month;
            }
            if (day.length() == 1 && Integer.parseInt(day) < 10) {
                day = "0" + day;
            }
            if (hour.length() == 1 && Integer.parseInt(hour) < 10) {
                hour = "0" + hour;
            }
            if (min.length() == 1 && Integer.parseInt(min) < 10) {
                min = "0" + min;
            }
            if (sec.length() == 1 && Integer.parseInt(sec) < 10) {
                sec = "0" + sec;
            }
            if (onChooseDateConfirmListener != null) {
                onChooseDateConfirmListener.onConfirm(year, month, day,hour,min,sec);
            }
        });

        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize);

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

        dgBinding.wvHour.setTextColorOut(textColor);
        dgBinding.wvHour.setTextColorCenter(textColorCenter);
        dgBinding.wvHour.setTextSize(textSize);
        dgBinding.wvHour.setLabel("时");

        dgBinding.wvMin.setTextColorOut(textColor);
        dgBinding.wvMin.setTextColorCenter(textColorCenter);
        dgBinding.wvMin.setTextSize(textSize);
        dgBinding.wvMin.setLabel("分");

        dgBinding.wvSec.setTextColorOut(textColor);
        dgBinding.wvSec.setTextColorCenter(textColorCenter);
        dgBinding.wvSec.setTextSize(textSize);
        dgBinding.wvSec.setLabel("秒");
    }

    //是否只显示中间的Label  默认为true
    public void isCenterLabel(boolean b) {
        dgBinding.wvYear.isCenterLabel(b);
        dgBinding.wvMonth.isCenterLabel(b);
        dgBinding.wvDay.isCenterLabel(b);
        dgBinding.wvHour.isCenterLabel(b);
        dgBinding.wvMin.isCenterLabel(b);
        dgBinding.wvSec.isCenterLabel(b);
    }



    public void setWheelViewStyle(int textColor, int textColorCenter, int textSize) {
        setWheelViewDefStyle(textColor,textColorCenter,textSize);
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
        dgBinding.wvHour.setLineSpacingMultiplier(lineHeight);
        dgBinding.wvMin.setLineSpacingMultiplier(lineHeight);
        dgBinding.wvSec.setLineSpacingMultiplier(lineHeight);

    }


    public interface OnChooseDateConfirmListener {
        void onConfirm(String year, String month, String day, String hour,String min,String sec);

    }

    private OnChooseDateConfirmListener onChooseDateConfirmListener;

    public void setOnChooseDateConfirmListener(OnChooseDateConfirmListener onChooseDateConfirmListener) {
        this.onChooseDateConfirmListener = onChooseDateConfirmListener;
    }
}
