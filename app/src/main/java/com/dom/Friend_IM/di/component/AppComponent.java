package com.dom.Friend_IM.di.component;


import com.dom.Friend_IM.app.App;
import com.dom.Friend_IM.di.module.AppModule;
import com.dom.Friend_IM.model.http.help.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App getContext();     //全局上下文

    RetrofitHelper retrofitHelper();  //Http 帮助类

}
