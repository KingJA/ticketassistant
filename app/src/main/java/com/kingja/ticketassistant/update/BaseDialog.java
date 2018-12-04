package com.kingja.ticketassistant.update;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;


import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.util.AppUtil;

import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/11/6 23:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDialog extends AlertDialog {
    protected OnConfirmListener onConfirmListener;
    protected OnCancelListener onCancelListener;


    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(true);
    }

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.Dialog_BASE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = (int) (AppUtil.getScreenWidth() * 0.8f);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

    }

    public interface OnConfirmListener {
        void onConfirm();
    }
    public interface OnCancelListener {
        void onCancel();
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }
}
