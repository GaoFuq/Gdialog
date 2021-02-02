package com.gfq.dialog.base;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * DATE_TIME 2021/2/2
 * AUTH gaofuq
 * DESCRIPTION
 */
public class MyOutLine extends ViewOutlineProvider {
    private int radius;
    private boolean clipBottom = true;

    public MyOutLine() {
    }

    public MyOutLine(int radius) {
        this.radius = radius;
    }

    public void setClipBottom(boolean clipBottom) {
        this.clipBottom = clipBottom;
    }

    public boolean isClipBottom() {
        return clipBottom;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        if (clipBottom) {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight() + radius, radius);
        } else {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
        }

    }

}
