package com.kingja.ticketassistant.fragment;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kingja.supershapeview.view.SuperShapeImageView;
import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.event.RefreshHeadImgEvent;
import com.kingja.ticketassistant.event.RefreshNicknameEvent;
import com.kingja.ticketassistant.imgaeloader.ImageLoader;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.page.ContactUsActivity;
import com.kingja.ticketassistant.page.HelpActivity;
import com.kingja.ticketassistant.page.headimg.PersonalActivity;
import com.kingja.ticketassistant.page.login.LoginActivity;
import com.kingja.ticketassistant.page.modifypassword.ModifyPasswordActivity;
import com.kingja.ticketassistant.util.AppUtil;
import com.kingja.ticketassistant.util.GoUtil;
import com.kingja.ticketassistant.util.LoginChecker;
import com.kingja.ticketassistant.util.SpSir;
import com.kingja.ticketassistant.util.ToastUtil;
import com.kingja.ticketassistant.view.StringTextView;
import com.sunmi.trans.util.AidlUtil;
import com.sunmi.trans.util.BytesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 1:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    SuperShapeImageView ivHead;
    @BindView(R.id.stv_nickName)
    StringTextView stvNickName;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.rl_contract)
    RelativeLayout rlContract;
    @BindView(R.id.rl_personal)
    RelativeLayout rlPersonal;
    @BindView(R.id.tv_quit)
    TextView tvQuit;

    @OnClick({R.id.rl_password, R.id.rl_contract, R.id.rl_personal, R.id.rl_useDes, R.id.tv_quit, R.id.rl_print})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_personal:
                //个人信息
                LoginChecker.goActivity(getActivity(), PersonalActivity.class);
                break;
            case R.id.rl_password:
                //修改密码
                LoginChecker.goActivity(getActivity(), ModifyPasswordActivity.class);
                break;
            case R.id.rl_contract:
                //联系我们
                GoUtil.goActivity(getActivity(), ContactUsActivity.class);
                break;
            case R.id.rl_useDes:
                //联系我们
                GoUtil.goActivity(getActivity(), HelpActivity.class);
                break;
            case R.id.rl_print:
                //测试打印
                ToastUtil.showText("测试打印");
//                AidlUtil.getInstance().printText("鸭鹿鸡验票系统打印测试",30,true,false);
//                AidlUtil.getInstance().printText("测试打印内容\n测试换行",30,true,false);
//                AidlUtil.getInstance().sendRawData(BytesUtil.printDiv());
                print();
                break;
            case R.id.tv_quit:
                //退出登录
                showQuitDialog();
                break;
            default:
                break;
        }
    }

    private void print() {
        AidlUtil.getInstance().printCenterType(1);
        AidlUtil.getInstance().printText("验票成功", AppUtil.dp2px(20), true, false);
        AidlUtil.getInstance().sendRawData(BytesUtil.printStarLineDiv());
        AidlUtil.getInstance().printCenterType(0);
        StringBuffer printContentSb = new StringBuffer();
        //标题
        String printContent = printContentSb.append("雁荡山一日游").append("\n")
                //订单号
                .append(String.format("订单号：%s", "000099")).append("\n")
                //游客
                .append(String.format("游客：%s  %s", "张三", "18888888888")).append("\n")
                //数量
                .append(String.format("数量：%d张", 3)).append("\n")
                //门市价
                .append(String.format("门市价：¥%d元", 200)).append("\n")
                //抵用金额
                .append(String.format("抵用金额：抵用%d元/张", 100)).append("\n")
                //使用期限
                .append(String.format("使用期限：%s", "2018-10-22至2018-10-25")).append("\n")
                //领券日期
                .append(String.format("领券日期：%s", "2018-10-22 10:22:10")).append("\n")
                .toString();

        AidlUtil.getInstance().printText(printContent, AppUtil.dp2px(12), false, false);
        AidlUtil.getInstance().printQr("https://github.com/KingJA",8,3);
        AidlUtil.getInstance().print3Line();
    }

    public void showQuitDialog() {
        new MaterialDialog.Builder(getActivity())
                .content("确认退出账号?")
                .positiveText("确认")
                .negativeText("取消")
                .positiveColor(ContextCompat.getColor(getActivity(), R.color.gray_hi))
                .onPositive((dialog, which) -> {
                    quit();
                })
                .show();
    }

    private void quit() {
        SpSir.getInstance().clearData();
        GoUtil.goActivityAndFinish(getActivity(), LoginActivity.class);
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        refreshPersonal();
    }

    private void refreshPersonal() {
        String token = SpSir.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            //已登录
            String nickname = SpSir.getInstance().getNickname();
            String headImg = SpSir.getInstance().getHeadImg();
            ImageLoader.getInstance().loadImage(getActivity(), headImg, ivHead);
            stvNickName.setString(nickname);
            ivHead.setOnClickListener(v -> {
                GoUtil.goActivity(getActivity(), PersonalActivity.class);
            });
        }
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNickname(RefreshNicknameEvent refreshNicknameEvent) {
        stvNickName.setText(SpSir.getInstance().getNickname());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setHeadImg(RefreshHeadImgEvent refreshHeadImgEvent) {
        String headImg = SpSir.getInstance().getHeadImg();
        ImageLoader.getInstance().loadImage(getActivity(), headImg, ivHead);
    }

}
