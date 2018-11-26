package com.kingja.ticketassistant.injector.component;


import android.app.Application;


import com.kingja.ticketassistant.injector.module.ApiModule;
import com.kingja.ticketassistant.injector.module.AppModule;
import com.kingja.ticketassistant.model.api.UserApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:42
 * 修改备注：
 */
@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent {
    UserApi getApi();
    Application getApplication();
}
