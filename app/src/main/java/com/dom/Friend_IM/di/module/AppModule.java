package com.dom.Friend_IM.di.module;


import com.dom.Friend_IM.app.App;
import com.dom.Friend_IM.model.http.help.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dom4j on 2017/3/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(){
        return new RetrofitHelper();
    }



}
