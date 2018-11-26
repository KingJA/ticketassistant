package com.kingja.ticketassistant.injector.module;




import com.kingja.ticketassistant.imgaeloader.GlideLoader;
import com.kingja.ticketassistant.imgaeloader.IImageLoader;

import dagger.Module;
import dagger.Provides;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Module
public class ImageLoaderModule {
    @Provides
    public IImageLoader provideImageLoader() {
        return new GlideLoader();
    }
}
