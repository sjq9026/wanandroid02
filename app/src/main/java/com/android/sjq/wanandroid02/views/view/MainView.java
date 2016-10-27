package com.android.sjq.wanandroid02.views.view;

import com.android.sjq.wanandroid02.adapters.ClassifyAdapter;
import com.android.sjq.wanandroid02.base.BaseView;

/**
 * Created by Administrator on 2016/10/18.
 */

public interface MainView extends BaseView {
    void setClassify(ClassifyAdapter adapter);
    void showDialog();
    void dissmissDialog();
}
