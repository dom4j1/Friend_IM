package com.dom.Friend_IM.presenter.contract;

import com.dom.Friend_IM.base.BasePresenter;
import com.dom.Friend_IM.base.BaseView;

/**
 * Created by dom4j on 2017/4/26.
 */

public interface MainContract {
    interface View extends BaseView{
         void showTest(String string);
    }
    interface Presenter extends BasePresenter<View>{
         void getTest(String string);
    }
}
