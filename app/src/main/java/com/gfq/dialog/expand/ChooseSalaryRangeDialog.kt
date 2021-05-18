package com.gfq.dialog.expand

import android.content.Context
import android.graphics.Color
import android.view.View
import com.contrarywind.adapter.WheelAdapter
import com.contrarywind.listener.OnItemSelectedListener
import com.gfq.dialog.R
import com.gfq.dialog.base.BaseDialog
import com.gfq.dialog.databinding.DialogChooseSalaryRangeBinding
import java.util.*

/**
 * @created GaoFuq
 * @Date 2020/7/22 16:32
 * @Descaption
 */
class ChooseSalaryRangeDialog(minSalary: Int, maxSalary: Int, label: String) : BaseDialog<DialogChooseSalaryRangeBinding>() {
    var minSalary = 1
    var maxSalary = 50
    var label = "k" //单位
    var wvTextColor = Color.parseColor("#999999")
    var wvTextColorCenter = Color.parseColor("#333333")
    var wvTextSize = 16
    private var wvMinAdapter: WheelAdapter<String>? = null
    private var wvMaxAdapter: WheelAdapter<String>? = null
    private var minSalaryList = mutableListOf<Int>()
    private var maxSalaryList =mutableListOf<Int>()
    private var selectedMinSalary = 0
    private var selectedMaxSalary = 0
    override fun layout(): Int {
        return R.layout.dialog_choose_salary_range
    }

    override fun bindView() {
        initData()
        bindDialogView()
    }

    private fun initData() {
        for (i in minSalary until maxSalary + 1) {
            minSalaryList.add(i)
        }
        for (i in minSalary + 1 until maxSalary + 1) {
            maxSalaryList.add(i)
        }
        wvMinAdapter = SalaryAdapter(minSalaryList)
        wvMaxAdapter = SalaryAdapter(maxSalaryList)
    }

    private fun bindDialogView() {
        dgBinding!!.wvMin.adapter = wvMinAdapter
        dgBinding!!.wvMax.adapter = wvMaxAdapter
        dgBinding!!.wvMin.setCyclic(false)
        dgBinding!!.wvMax.setCyclic(false)
        dgBinding!!.wvMin.currentItem = 0
        dgBinding!!.wvMax.currentItem = 0
        setWheelViewDefStyle(wvTextColor, wvTextColorCenter, wvTextSize)
        setLineHeight(3f)
        isCenterLabel(false)
        dgBinding!!.wvMin.setOnItemSelectedListener(OnItemSelectedListener { index ->
            selectedMinSalary = minSalaryList!![index]
            maxSalaryList!!.clear()
            if (selectedMinSalary == maxSalary) {
                maxSalaryList!!.add(selectedMinSalary)
                dgBinding!!.wvMax.adapter = SalaryAdapter(maxSalaryList)
                return@OnItemSelectedListener
            }
            if (selectedMinSalary >= selectedMaxSalary) {
                for (i in selectedMinSalary + 1 until maxSalary + 1) {
                    maxSalaryList!!.add(i)
                }
            }
            dgBinding!!.wvMax.adapter = SalaryAdapter(maxSalaryList)
            dgBinding!!.wvMax.currentItem = 0
        })
        dgBinding!!.wvMax.setOnItemSelectedListener { index -> selectedMaxSalary = maxSalaryList!![index] }
        dgBinding!!.tvConfirm.setOnClickListener { v: View? ->
            if (onConfirmListener != null) {
                onConfirmListener!!.onConfirm(selectedMinSalary.toString() + label, selectedMaxSalary.toString() + label)
            }
            dismiss()
        }
        dgBinding!!.tvCancel.setOnClickListener { v: View? -> dismiss() }
    }

    private fun setWheelViewDefStyle(textColor: Int, textColorCenter: Int, textSize: Int) {
        dgBinding!!.wvMin.setTextColorOut(textColor)
        dgBinding!!.wvMin.setTextColorCenter(textColorCenter)
        dgBinding!!.wvMin.setTextSize(textSize.toFloat())
        dgBinding!!.wvMin.setLabel(label)
        dgBinding!!.wvMax.setTextColorOut(textColor)
        dgBinding!!.wvMax.setTextColorCenter(textColorCenter)
        dgBinding!!.wvMax.setTextSize(textSize.toFloat())
        dgBinding!!.wvMax.setLabel(label)
    }

    //是否只显示中间的Label  默认为true
    fun isCenterLabel(b: Boolean) {
        dgBinding!!.wvMin.isCenterLabel(b)
        dgBinding!!.wvMax.isCenterLabel(b)
    }

    fun setWheelViewStyle(textColor: Int, textColorCenter: Int, textSize: Int) {
        setWheelViewDefStyle(textColor, textColorCenter, textSize)
    }

    fun setTitleStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding!!.tvTitle.text = text
        dgBinding!!.tvTitle.setTextColor(textColor)
        dgBinding!!.tvTitle.textSize = textSize.toFloat()
    }

    fun setConfirmStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding!!.tvConfirm.text = text
        dgBinding!!.tvConfirm.setTextColor(textColor)
        dgBinding!!.tvConfirm.textSize = textSize.toFloat()
    }

    fun setCancelStyle(text: String?, textColor: Int, textSize: Int) {
        dgBinding!!.tvCancel.text = text
        dgBinding!!.tvCancel.setTextColor(textColor)
        dgBinding!!.tvCancel.textSize = textSize.toFloat()
    }

    fun setLineHeight(lineHeight: Float) {
        dgBinding!!.wvMin.setLineSpacingMultiplier(lineHeight)
        dgBinding!!.wvMax.setLineSpacingMultiplier(lineHeight)
    }

    interface OnConfirmListener {
        fun onConfirm(minSalary: String?, maxSalary: String?)
    }

    private var onConfirmListener: OnConfirmListener? = null
    fun setOnConfirmListener(onConfirmListener: OnConfirmListener?) {
        this.onConfirmListener = onConfirmListener
    }

    internal class SalaryAdapter(private val list: List<Int>) : WheelAdapter<String> {
        override fun getItemsCount(): Int {
            return list.size
        }

        override fun getItem(index: Int): String {
            return list[index].toString() + ""
        }

        override fun indexOf(o: String): Int {
            return 0
        }
    }

    init {
        this.minSalary = minSalary
        this.maxSalary = maxSalary
        this.label = label
    }
}