package com.kingja.ticketassistant.page.modifynickname;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ModifyNicknameContract {
    interface View extends BaseView {
        void onModifyNicknameSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyNickname(String nickname);

    }
}
