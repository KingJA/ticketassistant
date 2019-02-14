package com.kingja.ticketassistant.page.check;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.constants.Constants;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.TicketInfo;
import com.kingja.ticketassistant.page.TicketDetailActivity;
import com.kingja.ticketassistant.util.CheckUtil;
import com.kingja.ticketassistant.util.DialogUtil;
import com.kingja.ticketassistant.util.ToastUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 1:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CheckFragment extends BaseFragment implements CheckContract.View {
    @BindView(R.id.et_code)
    EditText etCode;
    @Inject
    CheckPresenter checkPresenter;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    Unbinder unbinder;
    private RxPermissions rxPermissions;

    @OnClick({R.id.stv_num_one, R.id.stv_num_two, R.id.stv_num_three, R.id.stv_num_four, R.id.stv_num_five,
            R.id.stv_num_six, R.id.stv_num_seven, R.id.stv_num_eight, R.id.stv_num_nine, R.id.stv_num_zero,
            R.id.stv_num_check, R.id.stv_num_clear, R.id.iv_clear, R.id.iv_scan})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.stv_num_one:
                appenNum(1);
                break;
            case R.id.stv_num_two:
                appenNum(2);
                break;
            case R.id.stv_num_three:
                appenNum(3);
                break;
            case R.id.stv_num_four:
                appenNum(4);
                break;
            case R.id.stv_num_five:
                appenNum(5);
                break;
            case R.id.stv_num_six:
                appenNum(6);
                break;
            case R.id.stv_num_seven:
                appenNum(7);
                break;
            case R.id.stv_num_eight:
                appenNum(8);
                break;
            case R.id.stv_num_nine:
                appenNum(9);
                break;
            case R.id.stv_num_zero:
                appenNum(0);
                break;
            case R.id.stv_num_clear:
                String num = etCode.getText().toString().trim();
                if (num.length() > 0) {
                    String newNum = num.substring(0, num.length() - 1);
                    etCode.setText(newNum);
                    etCode.setSelection(etCode.getText().length());
                }
                break;
            case R.id.stv_num_check:
                String code = etCode.getText().toString().trim();
                if (CheckUtil.checkEmpty(code, "请输入验证码")) {
                    checkPresenter.checkTicket(code);
                }
                break;
            case R.id.iv_clear:
                etCode.setText("");
                break;
            case R.id.iv_scan:
                checkPhotoPermission();
                break;
            default:
                break;
        }
    }

    public void checkPhotoPermission() {
        Disposable disposable = rxPermissions.requestEach(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            openCamera();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            DialogUtil.showDoubleDialog(getActivity(), "为保证您使用二维码功能，需要获取相机权限，请允许", new MaterialDialog
                                    .SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    checkPhotoPermission();
                                }
                            });
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            DialogUtil.showDoubleDialog(getActivity(), "未取得相机权限，将无法使用二维码功能。请前往应用权限设置打开权限。", new
                                    MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                                                which) {
                                            startAppSettings();
                                        }
                                    });

                        }
                    }
                });

    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        startActivity(intent);
    }

    private void openCamera() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, Constants.RequestCode.QCODE);
    }

    private void appenNum(int num) {
        String newNum = etCode.getText().toString() + num;
        etCode.setText(newNum);
        etCode.setSelection(etCode.getText().length());
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        checkPresenter.attachView(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.QCODE:
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        result = result.substring(result.lastIndexOf("/") + 1);
                        etCode.setText(result);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }

    @Override
    protected void initView() {
        rxPermissions = new RxPermissions(this);
    }

    @Override
    protected void initData() {
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_check;
    }

    @Override
    public void onCheckTicketSuccess(TicketInfo ticketInfo) {
        if (ticketInfo != null) {
            TicketDetailActivity.goActivity(getActivity(), ticketInfo);
        } else {
            ToastUtil.showText("数据有误");
        }

    }
}
