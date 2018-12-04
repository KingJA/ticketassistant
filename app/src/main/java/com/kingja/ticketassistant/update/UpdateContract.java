package com.kingja.ticketassistant.update;



import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.VersionInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface UpdateContract {
    interface View extends BaseView {
        void onGetVersionSuccess(VersionInfo versionInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getVersion(String version, int flag);

    }
}
