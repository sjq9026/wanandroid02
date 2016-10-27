package com.android.sjq.wanandroid02.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sjq.wanandroid02.R;

public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>>
                                                    extends Fragment implements BaseView {
    public T mPresenter;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mPresenter.onAttach((V) this);
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    protected abstract T initPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showShorToast(String msg) {

    }
}
