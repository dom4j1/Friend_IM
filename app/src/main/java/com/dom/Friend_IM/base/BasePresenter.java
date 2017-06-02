package com.dom.Friend_IM.base;

/**
 * Created by dom4j on 2017/3/7.
 */

public interface BasePresenter<T extends BaseView>{

    /**
     *  订阅
     */
    void attachView(T view);

    /**
     *  注销
     */
    void detachView();
}
