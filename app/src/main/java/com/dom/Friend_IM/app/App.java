package com.dom.Friend_IM.app;

import android.app.Activity;
import android.app.Application;

import com.dom.Friend_IM.di.component.AppComponent;
import com.dom.Friend_IM.di.component.DaggerAppComponent;
import com.dom.Friend_IM.di.module.AppModule;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dom4j on 2017/3/7.
 */

public class App extends Application{

    private static App instance;

    private Set<Activity> allActivities;
    private static AppComponent appComponent;


    public static synchronized App getInstance(){return  instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        instance = this;

    }

    public void addActivity(Activity act){
        if(allActivities == null){
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act){
         if(allActivities != null){
             allActivities.remove(act);
         }
    }

    public void exitApp(){
        if(allActivities != null){
            synchronized (allActivities){
                for(Activity act : allActivities){
                    act.finish();
                }
            }
        }
        /**
         * 绕过Activity 生命周期  强制关闭
         */
        //android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }


}
