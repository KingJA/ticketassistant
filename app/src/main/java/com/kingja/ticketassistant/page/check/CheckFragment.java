package com.kingja.ticketassistant.page.check;

import android.view.View;
import android.widget.EditText;

import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.model.entiy.TicketInfo;
import com.kingja.ticketassistant.page.TicketDetailActivity;
import com.kingja.ticketassistant.util.CheckUtil;
import com.kingja.ticketassistant.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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
                ToastUtil.showText("二维码");
                break;
            default:
                break;
        }
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
    protected void initView() {

    }

    @Override
    protected void initData() {

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
        }else{
            ToastUtil.showText("数据有误");
        }

    }
}
