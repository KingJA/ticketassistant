package com.kingja.ticketassistant.page.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kingja.ticketassistant.page.home.HomeActivity;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseTitleActivity;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.constants.Constants;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.Login;
import com.kingja.ticketassistant.util.CheckUtil;
import com.kingja.ticketassistant.util.GoUtil;
import com.kingja.ticketassistant.util.SpSir;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 上午 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginActivity extends BaseTitleActivity implements LoginContract.View {

    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    @Inject
    LoginPresenter mLoginPresenter;

    @OnClick({R.id.tv_login_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_login_confirm:
                String username = etLoginName.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if (CheckUtil.checkPhoneFormat(username) && CheckUtil.checkEmpty(password, "请输入密码")) {
                    mLoginPresenter.login(username, password, username, "", Constants.OSNAME);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        if (!TextUtils.isEmpty(SpSir.getInstance().getToken())) {
            GoUtil.goActivityAndFinish(this, HomeActivity.class);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        mLoginPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }

    @Override
    protected boolean ifHideTitle() {
        return true;
    }

    @Override
    public void onLoginSuccess(Login account) {
        save2Localhost(account);
        GoUtil.goActivityAndFinish(this, HomeActivity.class);
    }

    private void save2Localhost(Login login) {
        SpSir.getInstance().putHeadImg(login.getHeadImg());
        SpSir.getInstance().putVerifierId(login.getVerifierId());
        SpSir.getInstance().putToken(login.getToken());
        SpSir.getInstance().putMobile(login.getMobile());
        SpSir.getInstance().putNickName(login.getNickName());
        SpSir.getInstance().putIsManager(login.getIsManager()==1);
    }
}
