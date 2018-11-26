package com.kingja.ticketassistant.page.modifypassword;

import android.view.View;
import android.widget.EditText;

import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseTitleActivity;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.util.CheckUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:修改密码
 * Create Time:2018/3/8 14:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyPasswordActivity extends BaseTitleActivity implements ModifyPasswordContract.View {
    @BindView(R.id.et_newPassword)
    EditText setModifyPasswordNew;
    @BindView(R.id.et_repeatPassword)
    EditText setModifyPasswordRepeat;
    @BindView(R.id.et_oldPassword)
    EditText setModifyPasswordOld;
    @Inject
    ModifyPasswordPresenter modifyPasswordPresenter;


    @OnClick({R.id.tv_confirm})
    public void click(View view) {
        String oldPassword = setModifyPasswordOld.getText().toString().trim();
        String newPassword = setModifyPasswordNew.getText().toString().trim();
        String repeatPassword = setModifyPasswordRepeat.getText().toString().trim();
        if (CheckUtil.checkEmpty(oldPassword, "请输入旧密码") && CheckUtil.checkEmpty(newPassword, "请输入新密码") && CheckUtil
                .checkEmpty(repeatPassword, "请输入重复密码") &&
                CheckUtil.checkSame(newPassword, repeatPassword, "两次输入密码不一致")) {
            modifyPasswordPresenter.modifyPassword(oldPassword, newPassword);
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        modifyPasswordPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "修改密码";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_modify_password;
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
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void onModifyPasswordSuccess() {
        showSuccessToastAndFinish("修改成功");
    }
}
