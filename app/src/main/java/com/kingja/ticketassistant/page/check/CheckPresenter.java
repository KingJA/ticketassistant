package com.kingja.ticketassistant.page.check;

import android.support.annotation.NonNull;

import com.kingja.ticketassistant.model.api.UserApi;
import com.kingja.ticketassistant.model.entiy.ResultObserver;
import com.kingja.ticketassistant.model.entiy.TicketInfo;
import com.kingja.ticketassistant.page.modifypassword.ModifyPasswordContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CheckPresenter implements CheckContract.Presenter {
    private UserApi mApi;
    private CheckContract.View mView;

    @Inject
    public CheckPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull CheckContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void checkTicket(String ticketCode) {
        mApi.getApiService().checkTicket( ticketCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe
                (new ResultObserver<TicketInfo>(mView) {
                    @Override
                    protected void onSuccess(TicketInfo ticketInfo) {
                        mView.onCheckTicketSuccess(ticketInfo);
                    }
                });
    }
}