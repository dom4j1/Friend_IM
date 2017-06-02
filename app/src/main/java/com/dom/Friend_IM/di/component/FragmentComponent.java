package com.dom.Friend_IM.di.component;

import android.app.Activity;


import com.dom.Friend_IM.di.FragmentScope;
import com.dom.Friend_IM.di.module.FragmentModule;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
     Activity getActivity();
}
