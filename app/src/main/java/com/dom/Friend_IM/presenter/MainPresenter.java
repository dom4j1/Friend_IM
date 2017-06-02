package com.dom.Friend_IM.presenter;

import com.dom.Friend_IM.base.RxPresenter;
import com.dom.Friend_IM.model.bean.TestBean;
import com.dom.Friend_IM.model.http.help.RetrofitHelper;
import com.dom.Friend_IM.model.http.help.RxHelper;
import com.dom.Friend_IM.model.http.help.Subscribe2Help;
import com.dom.Friend_IM.model.http.response.TestResult;
import com.dom.Friend_IM.presenter.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by dom4j on 2017/4/26.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public MainPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getTest(String string) {
        Observable<TestResult<List<TestBean>>> test = mRetrofitHelper.test(string, 2, 1);
        test.compose(RxHelper.<TestResult<List<TestBean>>>rxSchedulerHelper())
                .compose(RxHelper.<List<TestBean>>handleResult())
                .subscribe(new Subscribe2Help<List<TestBean>>(this) {
                    @Override
                    public void onNext(List<TestBean> value) {
                        mView.showTest(value.get(0).getDesc());
                    }
                });


    }
}
