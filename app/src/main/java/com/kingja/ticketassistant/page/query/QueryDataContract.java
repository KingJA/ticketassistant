package com.kingja.ticketassistant.page.query;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.CheckResult;

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
        void onQueryDataSuccess(CheckResult checkResult);
    }

    interface Presenter extends BasePresenter<View> {
        void queryData(RequestBody requestBody);

    }
}
