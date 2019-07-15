package com.kingja.ticketassistant.page.query;

import android.support.annotation.NonNull;

import com.kingja.ticketassistant.model.api.UserApi;
import com.kingja.ticketassistant.model.entiy.CheckResult;
import com.kingja.ticketassistant.model.entiy.LevelBean;
import com.kingja.ticketassistant.model.entiy.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryDataPresenter implements QueryDataContract.Presenter {
    private UserApi mApi;
    private QueryDataContract.View mView;

    @Inject
    public QueryDataPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull QueryDataContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void queryData(RequestBody requestBody) {
        mApi.getApiService().queryData(requestBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<LevelBean>>(mView) {
                    @Override
                    protected void onSuccess(List<LevelBean> levelBeanList) {
                        mView.onQueryDataSuccess(levelBeanList);
                    }
                });
    }
}