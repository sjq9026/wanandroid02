package com.android.sjq.wanandroid02.presenters;

import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.HomeBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.HomeView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class HomePresenter extends BasePresenter<HomeView> implements OnBlogItemClickListener {
    HomeBiz biz;

    public HomePresenter() {
        biz = new HomeBiz();
    }

    public void initRecentlyData() {
        biz.getRecentlyData(new onDataResult<RecentlyBlogAdapter>() {
            @Override
            public void onSuccess(RecentlyBlogAdapter adapter) {
                adapter.setOnItemClickListener(HomePresenter.this);
                view.setBlogSuc(adapter);
            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogNameClick(biz.getDataList().get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {
        view.onBlogClassifyClick(biz.getDataList().get(position));
    }
}
