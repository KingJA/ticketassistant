package com.kingja.ticketassistant.page.home;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.CheckResult;
import com.kingja.ticketassistant.model.entiy.ScenicType;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ScenicTypeContract {
    interface View extends BaseView {
        void onGetScenicTypeSuccess(List<ScenicType> scenicTypeList);
    }

    interface Presenter extends BasePresenter<View> {
        void getScenicType();

    }
}
