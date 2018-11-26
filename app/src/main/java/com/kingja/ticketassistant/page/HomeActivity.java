package com.kingja.ticketassistant.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseTitleActivity;
import com.kingja.ticketassistant.page.check.CheckFragment;
import com.kingja.ticketassistant.fragment.MineFragment;
import com.kingja.ticketassistant.fragment.QueryFragment;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseTitleActivity {


    @BindView(R.id.iv_tab_check)
    ImageView ivTabCheck;
    @BindView(R.id.tv_tab_check)
    TextView tvTabCheck;
    @BindView(R.id.iv_tab_query)
    ImageView ivTabQuery;
    @BindView(R.id.tv_tab_query)
    TextView tvTabQuery;
    @BindView(R.id.iv_tab_mine)
    ImageView ivTabMine;
    @BindView(R.id.tv_tab_mine)
    TextView tvTabMine;

    private FragmentManager supportFragmentManager;
    private Fragment currentFragment;
    private static SparseArray<Fragment> fragmentMap = new SparseArray<>();
    private static final int FRAGMENT_CHECK = 0;
    private static final int FRAGMENT_QUERY = 1;
    private static final int FRAGMENT_MINE = 2;
    private int currentTabIndex = 0;

    @OnClick({R.id.ll_tab_check, R.id.ll_tab_query, R.id.ll_tab_mine})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_check:
                switchFragment(FRAGMENT_CHECK);
                break;
            case R.id.ll_tab_query:
                switchFragment(FRAGMENT_QUERY);
                break;
            case R.id.ll_tab_mine:
                switchFragment(FRAGMENT_MINE);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "";
    }

    @Override
    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        fragmentMap.put(FRAGMENT_CHECK, currentFragment = new CheckFragment());
        fragmentMap.put(FRAGMENT_QUERY, new QueryFragment());
        fragmentMap.put(FRAGMENT_MINE, new MineFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.fl_home, currentFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }

    @Override
    protected boolean ifHideBack() {
        return true;
    }

    @Override
    protected boolean ifHideTitle() {
        return true;
    }

    private void switchFragment(int fragmentId) {
        if (fragmentId == currentTabIndex) {
            return;
        }
        Fragment targetFragment = fragmentMap.get(fragmentId);
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.fl_home, targetFragment).commit();
        } else {
            transaction.hide(currentFragment).show(targetFragment).commit();
        }
        currentFragment = targetFragment;
        currentTabIndex = fragmentId;
        setTabStatus(fragmentId);
    }

    private void setTabStatus(int fragmentId) {
        tvTabCheck.setTextColor(fragmentId == FRAGMENT_CHECK ? ContextCompat.getColor(this, R.color.orange_hi) :
                ContextCompat
                        .getColor(this, R.color.c_9));
        tvTabQuery.setTextColor(fragmentId == FRAGMENT_QUERY ? ContextCompat.getColor(this, R.color.orange_hi) :
                ContextCompat
                        .getColor(this, R.color.c_9));
        tvTabMine.setTextColor(fragmentId == FRAGMENT_MINE ? ContextCompat.getColor(this, R.color.orange_hi) :
                ContextCompat
                        .getColor(this, R.color.c_9));
        ivTabCheck.setBackgroundResource(fragmentId == FRAGMENT_CHECK ? R.mipmap.ic_check_sel : R.mipmap
                .ic_check_nor);
        ivTabQuery.setBackgroundResource(fragmentId == FRAGMENT_QUERY ? R.mipmap.ic_view_sel : R.mipmap
                .ic_view_nor);
        ivTabMine.setBackgroundResource(fragmentId == FRAGMENT_MINE ? R.mipmap.ic_mine_sel : R.mipmap
                .ic_mine_nor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentMap.clear();
    }

    //防止Fragment重生重叠
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    private long mLastTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastTime < 500) {
            finish();
            System.exit(0);
        } else {
            ToastUtil.showText("连续点击退出");
            mLastTime = currentTime;
        }
    }

}
