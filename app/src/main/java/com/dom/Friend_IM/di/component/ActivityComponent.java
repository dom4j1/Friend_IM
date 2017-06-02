package com.dom.Friend_IM.di.component;

import android.app.Activity;


import com.dom.Friend_IM.di.ActivityScope;
import com.dom.Friend_IM.di.module.ActivityModule;
import com.dom.Friend_IM.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
}
