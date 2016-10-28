package com.android.sjq.wanandroid02.presenters;

import com.android.sjq.wanandroid02.adapters.ClassifyAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.MainBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.MainView;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MainPresenter extends BasePresenter<MainView> {
    MainBiz mainBiz;

    public MainPresenter() {
        mainBiz = new MainBiz();
    }


    //获取大分类数据
    public void getClassify() {
        mainBiz.getClassify(new onDataResult<ClassifyAdapter>() {
            @Override
            public void onSuccess(ClassifyAdapter adapter) {
                view.setClassify(adapter);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
