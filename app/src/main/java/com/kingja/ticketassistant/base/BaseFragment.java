package com.kingja.ticketassistant.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.callback.ErrorMessageCallback;
import com.kingja.ticketassistant.callback.LoadingCallback;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.rx.RxRe;
import com.kingja.ticketassistant.util.LogUtil;
import com.kingja.ticketassistant.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created with Android Studio.
 * Login: ryan.hoo.j@gmail.com
 * Date: 3/16/16
 * Time: 12:14 AM
 * Desc: BaseFragment
 */
public abstract class BaseFragment extends Fragment implements BaseView, DialogInterface.OnDismissListener, BaseInit {
    protected String TAG = getClass().getSimpleName();
    private ProgressDialog mDialogProgress;
    protected Unbinder unbinder;
    protected LoadService mBaseLoadService;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable();
        initCommon();
        initComponent(App.getContext().getAppComponent());
        initView();
        initData();
        initNet();
    }


    protected abstract void initVariable();

    private void initCommon() {
        mDialogProgress = new ProgressDialog(getActivity());
        mDialogProgress.setCancelable(true);
        mDialogProgress.setCanceledOnTouchOutside(false);
        mDialogProgress.setOnDismissListener(this);
        mDialogProgress.setMessage("加载中");
    }

    /*设置圆形进度条*/
    protected void setProgressShow(boolean ifShow) {
        if (ifShow) {
            mDialogProgress.show();
        } else {
            mDialogProgress.dismiss();
        }
    }

    protected abstract void initComponent(AppComponent appComponent);

    protected abstract void initView();

    protected abstract void initData();

    public abstract void initNet();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View mRootView = inflater.inflate(getContentId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        if (ifRegisterLoadSir()) {
            mBaseLoadService = LoadSir.getDefault().register(mRootView, new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    onNetReload(v);
                }
            });
            return mBaseLoadService.getLoadLayout();
        }
        return mRootView;

    }

    protected void onNetReload(View v) {
        LogUtil.e(TAG, "onNetReload");
        initNet();
    }

    protected abstract int getContentId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxRe.getInstance().cancle(this);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mDialogProgress != null && mDialogProgress.isShowing()) {
            mDialogProgress.dismiss();
            mDialogProgress = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void showCusLoading() {

    }

    @Override
    public void hideCusLoading() {

    }

    @Override
    public boolean ifShowCusLoading() {
        return false;
    }

    @Override
    public void showLoadingCallback() {
        mBaseLoadService.showCallback(LoadingCallback.class);
    }

    @Override
    public void showEmptyCallback() {
    }

    @Override
    public void showErrorCallback() {
    }

    @Override
    public void showSuccessCallback() {
        mBaseLoadService.showSuccess();
    }

    @Override
    public void showErrorMessage(int code, String message) {
        if (ifRegisterLoadSir()) {
            mBaseLoadService.setCallBack(ErrorMessageCallback.class, new Transport() {
                @Override
                public void order(Context context, View view) {
                    TextView tvErrorMsg = view.findViewById(R.id.tv_layout_errorMsg);
                    tvErrorMsg.setText(message);
                }
            });
            mBaseLoadService.showCallback(ErrorMessageCallback.class);
        } else {
            ToastUtil.showText(message);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        RxRe.getInstance().cancle(this);
    }
}
