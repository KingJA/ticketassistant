package com.kingja.ticketassistant.fragment;

import com.kingja.ticketassistant.R;
import com.kingja.ticketassistant.base.BaseFragment;
import com.kingja.ticketassistant.base.DaggerBaseCompnent;
import com.kingja.ticketassistant.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/11/26 0026 下午 1:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryFragment extends BaseFragment {
    @Override
    protected void initVariable() {

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

    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_query;
    }
}
