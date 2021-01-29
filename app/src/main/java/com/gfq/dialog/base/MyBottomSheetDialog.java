package com.gfq.dialog.base;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import com.gfq.dialog.util.DensityUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * DATE_TIME 2021/1/29
 * AUTH gaofuq
 * DESCRIPTION
 */
public class MyBottomSheetDialog extends BottomSheetDialog {

    private float heightPercent;
    private int fixedHeight;//dp
    private int screenH;

    public MyBottomSheetDialog(Context context) {
        super(context);
    }

    public MyBottomSheetDialog(Context context, float heightPercent) {
        super(context);
        this.heightPercent = heightPercent;
    }

    public MyBottomSheetDialog(Context context, int fixedHeight) {
        super(context);
        this.fixedHeight = fixedHeight;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            Display mDisplay = window.getWindowManager().getDefaultDisplay();// 获取屏幕宽、高用
            Point p = new Point();
            mDisplay.getRealSize(p);
            screenH = p.y;
        }

        if (heightPercent >= 1) {
            heightPercent = 0;
        }

        if (fixedHeight >= screenH) {
            fixedHeight = 0;
        }


        if (heightPercent != 0) {
            fixedHeightPercent();
        }

        if (fixedHeight != 0) {
            fixedHeight();
        }
    }

    private void fixedHeightPercent() {
        Window window = getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenH * heightPercent));
            window.setGravity(Gravity.BOTTOM);
            getBehavior().setPeekHeight((int) (screenH * heightPercent));
        }

    }

    private void fixedHeight() {
        Window window = getWindow();
        if (window != null) {
            int height = DensityUtil.dp2px(fixedHeight);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);
            window.setGravity(Gravity.BOTTOM);
            getBehavior().setPeekHeight(height);
        }

    }


}
