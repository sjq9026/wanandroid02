package com.android.sjq.wanandroid02.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

    public abstract T initPresenter();

    @Override
    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShorToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
