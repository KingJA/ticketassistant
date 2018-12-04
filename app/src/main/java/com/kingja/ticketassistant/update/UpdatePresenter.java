package com.kingja.ticketassistant.update;

import android.support.annotation.NonNull;


import com.kingja.ticketassistant.model.api.UserApi;
import com.kingja.ticketassistant.model.entiy.ResultObserver;
import com.kingja.ticketassistant.model.entiy.VersionInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdatePresenter implements UpdateContract.Presenter {
    private UserApi mApi;
    private UpdateContract.View mView;

    @Inject
    public UpdatePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getVersion(String version, int flag) {
        mApi.getApiService().getVersion(version, flag, 2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<VersionInfo>(mView) {
                    @Override
                    protected void onSuccess(VersionInfo versionInfo) {
                        mView.onGetVersionSuccess(versionInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull UpdateContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}