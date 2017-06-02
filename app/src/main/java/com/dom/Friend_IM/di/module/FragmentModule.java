package com.dom.Friend_IM.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.dom.Friend_IM.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dom4j on 2017/3/7.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return fragment.getActivity();
    }

}
