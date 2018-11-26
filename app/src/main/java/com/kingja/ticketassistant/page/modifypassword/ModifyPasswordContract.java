package com.kingja.ticketassistant.page.modifypassword;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyPasswordContract {
    interface View extends BaseView {
        void onModifyPasswordSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyPassword(String oldPassword, String newPassword);

    }
}
