package com.dom.Friend_IM.di.module;

import android.app.Activity;


import com.dom.Friend_IM.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dom4j on 2017/3/7.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity providerActivity(){
        return mActivity;
    }
}
