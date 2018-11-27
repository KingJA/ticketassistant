package com.kingja.ticketassistant.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.kingja.loadsir.core.LoadSir;
import com.kingja.ticketassistant.callback.ErrorMessageCallback;
import com.kingja.ticketassistant.callback.LoadingCallback;
import com.kingja.ticketassistant.constants.Constants;
import com.kingja.ticketassistant.injector.component.AppComponent;
import com.kingja.ticketassistant.injector.component.DaggerAppComponent;
import com.kingja.ticketassistant.injector.module.ApiModule;
import com.kingja.ticketassistant.injector.module.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Description：App
 * Create Time：2016/10/14:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class App extends Application {
    private static App sInstance;
    private AppComponent appComponent;
    private static SharedPreferences mSharedPreferences;
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initLoadSir();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        ZXingLibrary.initDisplayOpinion(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        sInstance = this;
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE);
        setupComponent();
    }

    public static SharedPreferences getSp() {
        return mSharedPreferences;
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorMessageCallback())
                .addCallback(new LoadingCallback())
                .commit();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
        appModule = new AppModule(this);
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppModule getAppModule() {
        return appModule;
    }
}
