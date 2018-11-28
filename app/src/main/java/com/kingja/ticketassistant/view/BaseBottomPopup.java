package com.kingja.ticketassistant.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.kingja.ticketassistant.R;

import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/11/18 23:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseBottomPopup extends PopupWindow implements PopupWindow.OnDismissListener {
    protected Context context;
    private View parentView;

    public BaseBottomPopup(Context context, View parentView) {
        super(context);
        this.context = context;
        this.parentView = parentView;
        View rootView = View.inflate(context, getLayoutId(), null);
        setContentView(rootView);
        ButterKnife.bind(this, rootView);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(getPopHeight());
        setBackgroundDrawable(new ColorDrawable());
        setOnDismissListener(this);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.popwin_anim_style);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    public void showPopupAbove() {
        if (!this.isShowing()) {
            this.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            setAlpha(0.7f);
        }
    }

    protected int getPopHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public void onDismiss() {
        setAlpha(1f);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setAlpha(1f);
    }

    private void setAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

}
