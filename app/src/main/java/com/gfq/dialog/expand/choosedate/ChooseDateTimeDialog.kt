package com.gfq.dialog.expand.choosedate

import android.content.Context
import android.graphics.Color
import android.view.View
import com.contrarywind.adapter.WheelAdapter
import com.gfq.dialog.R
import com.gfq.dialog.base.BaseDialog
import com.gfq.dialog.databinding.DialogChooseDateBinding
import java.util.*
import kotlin.math.max

class ChooseDateTimeDialog : BaseDialog<DialogChooseDateBinding>() {
    private lateinit var yearAdapter: WheelAdapter<Int>
    private lateinit var monthAdapter: WheelAdapter<Int>
    private lateinit var dayAdapter: WheelAdapter<Int>
    private lateinit var hourAdapter: WheelAdapter<Int>
    private lateinit var minAdapter: WheelAdapter<Int>
    private lateinit var secAdapter: WheelAdapter<Int>
    private var yearList= mutableListOf<Int>()
    private var monthList = mutableListOf<Int>()
    private var dayList = mutableListOf<Int>()
    private var hourList = mutableListOf<Int>()
    private var minList = mutableListOf<Int>()
    private var secList = mutableListOf<Int>()
    private var year = ""
    private var month = ""
    private var day = ""
    private var hour = ""
    private var min = ""
    private var sec = ""
    private val wvTextColor = Color.parseColor("#999999")
    private val wvTextColorCenter = Color.parseColor("#333333")
    private val wvTextSize = 16
    override fun layout(): Int {
        return R.layout.dialog_choose_date
    }

    override fun bindView() {
        initData()
        setDateType(DateType.year_month_day)
        bindDialogView()
    }

    fun setDateType(dateType: DateType) {
        if (dateType == DateType.year_month_day) {
            dgBinding.llYear.visibility = View.VISIBLE
            dgBinding.llMonth.visibility = View.VISIBLE
            dgBinding.llDay.visibility = View.VISIBLE
            dgBinding.llHour.visibility = View.GONE
            dgBinding.llMin.visibility = View.GONE
            dgBinding.llSec.visibility = View.GONE
        } else if (dateType == DateType.year_month) {
            dgBinding.llYear.visibility = View.VISIBLE
            dgBinding.llMonth.visibility = View.VISIBLE
            dgBinding.llDay.visibility = View.GONE
            dgBinding.llHour.visibility = View.GONE
            dgBinding.llMin.visibility = View.GONE
            dgBinding.llSec.visibility = View.GONE
        } else if (dateType == DateType.month_day) {
            dgBinding.llMonth.visibility = View.VISIBLE
            dgBinding.llDay.visibility = View.VISIBLE
            dgBinding.llYear.visibility = View.GONE
            dgBinding.llHour.visibility = View.GONE
            dgBinding.llMin.visibility = View.GONE
            dgBinding.llSec.visibility = View.GONE
        } else if (dateType == DateType.hour_min_second) {
            dgBinding.llHour.visibility = View.VISIBLE
            dgBinding.llMin.visibility = View.VISIBLE
            dgBinding.llSec.visibility = View.VISIBLE
            dgBinding.llYear.visibility = View.GONE
            dgBinding.llMonth.visibility = View.GONE
            dgBinding.llDay.visibility = View.GONE
        } else if (dateType == DateType.hour_min) {
            dgBinding.llHour.visibility = View.VISIBLE
            dgBinding.llMin.visibility = View.VISIBLE
            dgBinding.llSec.visibility = View.GONE
            dgBinding.llYear.visibility = View.GONE
            dgBinding.llMonth.visibility = View.GONE
            dgBinding.llDay.visibility = View.GONE
        } else if (dateType == DateType.min_second) {
            dgBinding.llMin.visibility = View.VISIBLE
            dgBinding.llSec.visibility = View.VISIBLE
            dgBinding.llHour.visibility = View.GONE
            dgBinding.llYear.visibility = View.GONE
            dgBinding.llMonth.visibility = View.GONE
            dgBinding.llDay.visibility = View.GONE
        }
    }

    private fun initData() {
        val year = Calendar.getInstance()[Calendar.YEAR]
        for (i in year until year + 3) {
            yearList.add(i)
        }
        for (i in 1..12) {
            monthList.add(i)
        }
        for (i in 1..31) {
            dayList.add(i)
        }
        for (i in 0..23) {
            hourList.add(i)
        }
        for (i in 0..59) {
            minList.add(i)
            secList.add(i)
        }
        yearAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return yearList.size
            }

            override fun getItem(index: Int): Int {
                return yearList[index]
            }

            override fun indexOf(o: Int): Int {
                return yearList.indexOf(o)
            }
        }
        monthAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return monthList.size
            }

            override fun getItem(index: Int): Int {
                return monthList[index]
            }

            override fun indexOf(o: Int): Int {
                return monthList.indexOf(o)
            }
        }
        dayAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return dayList.size
            }

            override fun getItem(index: Int): Int {
                return dayList[index]
            }

            override fun indexOf(o: Int): Int {
                return dayList.indexOf(o)
            }
        }
        hourAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return hourList.size
            }

            override fun getItem(index: Int): Int {
                return hourList[index]
            }

            override fun indexOf(o: Int): Int {
                return hourList.indexOf(o)
            }
        }
        minAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return minList.size
            }

            override fun getItem(index: Int): Int {
                return minList[index]
            }

            override fun indexOf(o: Int): Int {
                return minList.indexOf(o)
            }
        }
        secAdapter = object : WheelAdapter<Int> {
            override fun getItemsCount(): Int {
                return secList.size
            }

            override fun getItem(index: Int): Int {
                return secList[index]
            }

            override fun indexOf(o: Int): Int {
                return secList.indexOf(o)
            }
        }
    }

    private fun bindDialogView() {
        dgBinding.wvYear.adapter = yearAdapter
        dgBinding.wvMonth.adapter = monthAdapter
        dgBinding.wvDay.adapter = dayAdapter
        dgBinding.wvHour.adapter = hourAdapter
        dgBinding.wvMin.adapter = minAdapter
        dgBinding.wvSec.adapter = secAdapter
        dgBinding.wvYear.setCyclic(false)
        dgBinding.wvMonth.setCyclic(false)
        dgBinding.wvDay.setCyclic(false)
        dgBinding.wvHour.setCyclic(false)
        dgBinding.wvMin.setCyclic(false)
        dgBinding.wvSec.setCyclic(false)
        dgBinding.wvYear.currentItem = 0
        val m = Calendar.getInstance(Locale.CHINA)[Calendar.MONTH]
        dgBinding.wvMonth.currentItem = m
        val d = Calendar.getInstance(Locale.CHINA)[Calendar.DAY_OF_MONTH]
        dgBinding.wvDay.currentItem = Math.max(d - 1, 0)
        val h = Calendar.getInstance(Locale.CHINA)[Calendar.HOUR_OF_DAY]
        dgBinding.wvHour.currentItem = Math.max(h - 1, 0)
        val minute = Calendar.getInstance(Locale.CHINA)[Calendar.MINUTE]
        dgBinding.wvMin.currentItem = Math.max(minute - 1, 0)
        val second = Calendar.getInstance(Locale.CHINA)[Calendar.SECOND]
        dgBinding.wvSec.currentItem = Math.max(second - 1, 0)
        setLineHeight(3f)
        isCenterLabel(false)
        dgBinding.wvYear.setOnItemSelectedListener { index: Int -> year = yearAdapter.getItem(index).toString() }
        dgBinding.wvMonth.setOnItemSelectedListener { index: Int -> month = monthAdapter.getItem(index).toString()}
        dgBinding.wvDay.setOnItemSelectedListener { index: Int -> day = dayAdapter.getItem(index).toString() }
        dgBinding.wvHour.setOnItemSelectedListener { index: Int -> hour = hourAdapter.getItem(index).toString() }
        dgBinding.wvMin.setOnItemSelectedListener { index: Int -> min = minAdapter.getItem(index).toString() }
        dgBinding.wvSec.setOnItemSelectedListener { index: Int -> sec = secAdapter.getItem(index).toString()}
        dgBinding.tvCancel.setOnClickListener { v: View? -> dismiss() }
        dgBinding.tvConfirm.setOnClickListener { v: View? ->
            dismiss()
            if (year == "") {
                year = yearAdapter.getItem(0).toString() + ""
            }
            if (month == "") {
                month = monthAdapter.getItem(m).toString() + ""
            }
            if (day == "") {
                day = dayAdapter.getItem(max(d - 1, 0)).toString() + ""
            }
            if (hour == "") {
                hour = hourAdapter.getItem(max(h - 1, 0)).toString() + ""
            }
            if (min == "") {
                min = minAdapter.getItem(max(minute - 1, 0)).toString() + ""
            }
            if (sec == "") {
                sec = secAdapter.getItem(max(second - 1, 0)).toString() + ""
            }
            if (month.length == 1 && month.toInt() < 10) {
                month = "0$month"
            }
            if (day.length == 1 && day.toInt() < 10) {
                day = "0$day"
            }
            if (hour.length == 1 && hour.toInt() < 10) {
                hour = "0$hour"
            }
            if (min.length == 1 && min.toInt() < 10) {
                min = "0$min"
            }
            if (sec.length == 1 && sec.toInt() < 10) {
                sec = "0$sec"
            }
            if (onChooseDateConfirmListener != null) {
                onChooseDateConfirmListener!!.onConfirm(year, month, day, hour, min, sec)
            }
        }
        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize)
    }

    private fun setWheelViewDefStyle(textColor: Int, textColorCenter: Int, textSize: Int) {
        dgBinding.wvYear.setTextColorOut(textColor)
        dgBinding.wvYear.setTextColorCenter(textColorCenter)
        dgBinding.wvYear.setTextSize(textSize.toFloat())
        dgBinding.wvYear.setLabel("年")
        dgBinding.wvMonth.setTextColorOut(textColor)
        dgBinding.wvMonth.setTextColorCenter(textColorCenter)
        dgBinding.wvMonth.setTextSize(textSize.toFloat())
        dgBinding.wvMonth.setLabel("月")
        dgBinding.wvDay.setTextColorOut(textColor)
        dgBinding.wvDay.setTextColorCenter(textColorCenter)
        dgBinding.wvDay.setTextSize(textSize.toFloat())
        dgBinding.wvDay.setLabel("日")
        dgBinding.wvHour.setTextColorOut(textColor)
        dgBinding.wvHour.setTextColorCenter(textColorCenter)
        dgBinding.wvHour.setTextSize(textSize.toFloat())
        dgBinding.wvHour.setLabel("时")
        dgBinding.wvMin.setTextColorOut(textColor)
        dgBinding.wvMin.setTextColorCenter(textColorCenter)
        dgBinding.wvMin.setTextSize(textSize.toFloat())
        dgBinding.wvMin.setLabel("分")
        dgBinding.wvSec.setTextColorOut(textColor)
        dgBinding.wvSec.setTextColorCenter(textColorCenter)
        dgBinding.wvSec.setTextSize(textSize.toFloat())
        dgBinding.wvSec.setLabel("秒")
    }

    //是否只显示中间的Label  默认为true
    fun isCenterLabel(b: Boolean) {
        dgBinding.wvYear.isCenterLabel(b)
        dgBinding.wvMonth.isCenterLabel(b)
        dgBinding.wvDay.isCenterLabel(b)
        dgBinding.wvHour.isCenterLabel(b)
        dgBinding.wvMin.isCenterLabel(b)
        dgBinding.wvSec.isCenterLabel(b)
    }

    fun setWheelViewStyle(textColor: Int, textColorCenter: Int, textSize: Int) {
        setWheelViewDefStyle(textColor, textColorCenter, textSize)
    }

    fun setTitleStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding.tvTitle.text = text
        dgBinding.tvTitle.setTextColor(textColor)
        dgBinding.tvTitle.textSize = textSize.toFloat()
    }

    fun setConfirmStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding.tvConfirm.text = text
        dgBinding.tvConfirm.setTextColor(textColor)
        dgBinding.tvConfirm.textSize = textSize.toFloat()
    }

    fun setCancelStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding.tvCancel.text = text
        dgBinding.tvCancel.setTextColor(textColor)
        dgBinding.tvCancel.textSize = textSize.toFloat()
    }

    fun setLineHeight(lineHeight: Float) {
        dgBinding.wvYear.setLineSpacingMultiplier(lineHeight)
        dgBinding.wvMonth.setLineSpacingMultiplier(lineHeight)
        dgBinding.wvDay.setLineSpacingMultiplier(lineHeight)
        dgBinding.wvHour.setLineSpacingMultiplier(lineHeight)
        dgBinding.wvMin.setLineSpacingMultiplier(lineHeight)
        dgBinding.wvSec.setLineSpacingMultiplier(lineHeight)
    }

    interface OnChooseDateConfirmListener {
        fun onConfirm(year: String, month: String, day: String, hour: String, min: String, sec: String)
    }

    private var onChooseDateConfirmListener: OnChooseDateConfirmListener? = null
    
    fun setOnChooseDateConfirmListener(onChooseDateConfirmListener: OnChooseDateConfirmListener?) {
        this.onChooseDateConfirmListener = onChooseDateConfirmListener
    }
}