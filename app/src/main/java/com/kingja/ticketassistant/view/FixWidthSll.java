package com.kingja.ticketassistant.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.kingja.supershapeview.view.SuperShapeLinearLayout;


/**
 * Description:TODO
 * Create Time:2018/5/11 14:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FixWidthSll extends SuperShapeLinearLayout {
    public FixWidthSll(Context context) {
        super(context);
    }

    public FixWidthSll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixWidthSll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
