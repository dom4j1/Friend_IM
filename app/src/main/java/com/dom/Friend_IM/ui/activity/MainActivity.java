package com.dom.Friend_IM.ui.activity;


import android.widget.TextView;

import com.dom.Friend_IM.R;
import com.dom.Friend_IM.base.BaseActivity;
import com.dom.Friend_IM.presenter.MainPresenter;
import com.dom.Friend_IM.presenter.contract.MainContract;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{


    @Override
    public void showError(String msg) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getTest("Android");
    }

    @Override
    public void showTest(String string) {
        TextView view = (TextView) findViewById(R.id.test_tv);
        view.setText(string);
    }
}
