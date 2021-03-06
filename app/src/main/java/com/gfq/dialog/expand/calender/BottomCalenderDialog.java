package com.gfq.dialog.expand.calender;

import android.content.Context;

import com.gfq.dialog.R;
import com.gfq.dialog.base.BaseBottomDialog;
import com.gfq.dialog.databinding.BottomCalenderBinding;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.Map;

/**
 * create by 高富强
 * on {2019/10/17} {11:21}
 * desctapion:
 */
public class BottomCalenderDialog {
    private BaseBottomDialog<BottomCalenderBinding> dialog;
    private BottomCalenderBinding binding;
    private Map<String, Calendar> map;
    private int year;
    private int month;
    private int day;


    public BottomCalenderDialog(Context context) {
        init(context);
    }


    private void init(Context context) {
        map = new HashMap<>();
        dialog = new BaseBottomDialog<BottomCalenderBinding>(context) {
            @Override
            protected int layout() {
                return R.layout.bottom_calender;
            }

            @Override
            protected void bindView() {
                bindDialogView();
                binding = dgBinding;
            }
        };

    }

    private void bindDialogView() {
        year = binding.calendarView.getCurYear();
        month = binding.calendarView.getCurMonth();
        binding.tvYear.setText(year + "");
        binding.tvMonth.setText(month + "");
        day = binding.calendarView.getCurDay();
        map.put(getSchemeCalendar(year, month, day, 0xFF3461E8, "今").toString(), getSchemeCalendar(year, month, day, 0xFF3461E8, "今"));
        binding.calendarView.setSchemeDate(map);
        binding.calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                year = calendar.getYear();
                month = calendar.getMonth();
                binding.tvYear.setText(year + "");
                binding.tvMonth.setText(month + "");
                if (onCalenderSelectListener != null) {
                    onCalenderSelectListener.onCalenderSelected(year + "", month + "", calendar.getDay() + "");
                }
            }
        });
        binding.ivLeftBackOne.setOnClickListener(v -> binding.calendarView.scrollToPre(true));
        binding.ivRightBackOne.setOnClickListener(v -> binding.calendarView.scrollToNext(true));

        binding.ivLeftBackDouble.setOnClickListener(v -> {
            String tempYear = binding.tvYear.getText().toString();
            String tempMonth = binding.tvMonth.getText().toString();
            int year = Integer.parseInt(tempYear) - 1;
            int month = Integer.parseInt(tempMonth);
            binding.calendarView.scrollToCalendar(year, month, 1, true);
        });


        binding.ivRightBackDouble.setOnClickListener(v -> {
            String tempYear = binding.tvYear.getText().toString();
            String tempMonth = binding.tvMonth.getText().toString();
            int year = Integer.parseInt(tempYear) + 1;
            int month = Integer.parseInt(tempMonth);
            binding.calendarView.scrollToCalendar(year, month, 1, true);
        });

    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    public void setCurrentDate(int year, int month, int day) {
        binding.calendarView.scrollToCalendar(year, month, day);
    }

    public interface OnCalenderSelectListener {
        void onCalenderSelected(String year, String month, String day);
    }

    private OnCalenderSelectListener onCalenderSelectListener;

    public void setOnCalenderSelectListener(OnCalenderSelectListener onCalenderSelectListener) {
        this.onCalenderSelectListener = onCalenderSelectListener;
    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
