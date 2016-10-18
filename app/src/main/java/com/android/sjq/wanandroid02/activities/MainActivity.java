package com.android.sjq.wanandroid02.activities;

import android.os.Bundle;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.base.BaseMvpActivity;
import com.android.sjq.wanandroid02.presenters.MainPresenter;
import com.android.sjq.wanandroid02.views.MainView;

public class MainActivity extends BaseMvpActivity<MainView,MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.getClassify();
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setClassify() {

    }
}
