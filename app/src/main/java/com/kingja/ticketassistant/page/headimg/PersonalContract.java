package com.kingja.ticketassistant.page.headimg;



import com.kingja.ticketassistant.base.BasePresenter;
import com.kingja.ticketassistant.base.BaseView;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PersonalContract {
    interface View extends BaseView {
        void onUploadHeadImgSuccess(String url);
    }

    interface Presenter extends BasePresenter<View> {
        void uploadHeadImg(MultipartBody.Part headImg);

    }
}
