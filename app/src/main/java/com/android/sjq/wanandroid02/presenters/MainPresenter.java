package com.android.sjq.wanandroid02.presenters;

import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.views.MainView;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MainPresenter extends BasePresenter<MainView> {
    public void getClassify() {
        view.setClassify();
    }
}
