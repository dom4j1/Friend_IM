package com.dom.Friend_IM.model.http.help;

import android.util.Log;


import com.dom.Friend_IM.base.RxPresenter;

import java.net.ConnectException;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by dom4j on 2017/3/8.
 */

public abstract class Subscribe2Help<T> implements Observer<T> {

    RxPresenter mPresenter;
    public Subscribe2Help(RxPresenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mPresenter.addSubscribe(d);
    }

    @Override
    public void onComplete() {}

    @Override
    public void onError(Throwable throwable) {
        if(throwable instanceof ApiExcpetion){
            Log.d("TAG"," 服务器异常 : " +throwable);
        }else if(throwable instanceof ConnectException){
            Log.d("TAG"," 连接超时 : " +throwable);
        }else if(throwable instanceof SocketException){
            Log.d("TAG"," 连接超时 : " +throwable);
        }else if(throwable instanceof HttpException){
            Log.d("TAG","请检查您的网络连接 稍后重试" + " =￣ω￣=");
        }

    }
}
