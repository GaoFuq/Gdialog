package com.gfq.dialog.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.FloatRange;

import com.gfq.dialog.expand.calender.BottomCalenderDialog;
import com.gfq.dialog.util.DensityUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * DATE_TIME 2021/1/29
 * AUTH gaofuq
 * DESCRIPTION
 */
public class MyBottomSheetDialog extends BottomSheetDialog {

    private float heightPercent;
    private float widthPercent;
    private int fixedHeight;
    private int fixedWidth;
    private Window window;
    private int horizontalMargin;
    private int verticalMargin;
    private int gravity = Gravity.BOTTOM;
    private int screenH;
    private int screenW;


    public MyBottomSheetDialog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = getWindow();
        if (window != null) {
            Display display = window.getWindowManager().getDefaultDisplay();
            Point p = new Point();
            display.getRealSize(p);
            screenH = p.y;
            screenW = p.x;
        }
    }



    public void setWidthPercent(float widthPercent) {
        this.widthPercent = widthPercent;
        getBehavior().setPeekHeight((int) (screenW * widthPercent));
        setAttributes();
    }

    public void setFixedWidth(int fixedWidth) {
        this.fixedWidth = fixedWidth;
        setAttributes();
    }

    public void setHeightPercent(float heightPercent) {
        this.heightPercent = heightPercent;
        getBehavior().setPeekHeight((int) (screenH * heightPercent));
        setAttributes();
    }
    public void setFixedHeight(int fixedHeight) {
        this.fixedHeight = fixedHeight;
        setAttributes();
    }

    public void setGravity(int gravity){
        this.gravity = gravity;
        setAttributes();
    }


    public void setHorizontalMargin(int horizontalMargin){
        this.horizontalMargin = horizontalMargin;
        setAttributes();
    }

    public void setVerticalMargin(int verticalMargin){
        this.verticalMargin = verticalMargin;
        setAttributes();
    }





    private void setAttributes() {
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();


            lp.x = horizontalMargin;
            lp.y = verticalMargin;


            if (widthPercent > 0) {
                lp.width = (int) (screenW * widthPercent);
            }

            if (fixedWidth > 0) {
                lp.width = fixedWidth;
            }


            if (heightPercent > 0) {
                lp.height = (int) (screenH * heightPercent);
            }
            if (fixedHeight > 0) {
                lp.height = fixedHeight;
            }

            if (gravity != 0) {
                window.setGravity(gravity);
            }

            window.setAttributes(lp);

        }
    }


}
