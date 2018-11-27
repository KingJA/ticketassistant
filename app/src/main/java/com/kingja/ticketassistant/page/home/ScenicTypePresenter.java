package com.kingja.ticketassistant.page.home;

import android.support.annotation.NonNull;

import com.kingja.ticketassistant.model.api.UserApi;
import com.kingja.ticketassistant.model.entiy.ResultObserver;
import com.kingja.ticketassistant.model.entiy.ScenicType;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ScenicTypePresenter implements ScenicTypeContract.Presenter {
    private UserApi mApi;
    private ScenicTypeContract.View mView;

    @Inject
    public ScenicTypePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull ScenicTypeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getScenicType() {
        mApi.getApiService().getScenicType().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<List<ScenicType>>(mView) {
                    @Override
                    protected void onSuccess(List<ScenicType> scenicTypeList) {
                        mView.onGetScenicTypeSuccess(scenicTypeList);
                    }
                });
    }
}