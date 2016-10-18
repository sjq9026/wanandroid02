package com.android.sjq.wanandroid02.views;

import com.android.sjq.wanandroid02.base.BaseView;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/18.
 */

public interface MainView extends BaseView {
    void setClassify(ArrayList<ClassifyEntity> list);
}
