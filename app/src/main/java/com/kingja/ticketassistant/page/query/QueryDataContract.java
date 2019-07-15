package com.kingja.ticketassistant.page.query;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.CheckResult;
import com.kingja.ticketassistant.model.entiy.LevelBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface QueryDataContract {
    interface View extends BaseView {
        void onQueryDataSuccess(List<LevelBean> levelBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void queryData(RequestBody requestBody);

    }
}
