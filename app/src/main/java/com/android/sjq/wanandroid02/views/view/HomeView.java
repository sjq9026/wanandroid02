package com.android.sjq.wanandroid02.views.view;

import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseView;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface HomeView extends BaseView {
    void setBlogSuc(RecentlyBlogAdapter adapter);
    void onBlogNameClick(RecentlyBlogInfoEntity entity);
    void onBlogClassifyClick(RecentlyBlogInfoEntity entity);
}
