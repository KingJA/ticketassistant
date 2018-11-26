package com.kingja.ticketassistant.page.login;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.Login;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(Login account);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String mobile, String password, String deviceId, String deviceName,
                   String osName);

    }
}
