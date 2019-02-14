package com.kingja.ticketassistant.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.kingja.supershapeview.view.SuperShapeTextView;


/**
 * Description:TODO
 * Create Time:2018/5/11 14:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FixWidthStv extends SuperShapeTextView {
    public FixWidthStv(Context context) {
        super(context);
    }

    public FixWidthStv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixWidthStv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}
