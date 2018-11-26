package com.kingja.ticketassistant.page.check;


import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;
import com.kingja.ticketassistant.model.entiy.TicketInfo;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface CheckContract {
    interface View extends BaseView {
        void onCheckTicketSuccess(TicketInfo ticketInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void checkTicket(String ticketCode);

    }
}
