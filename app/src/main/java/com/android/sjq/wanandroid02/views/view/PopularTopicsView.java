package com.android.sjq.wanandroid02.views.view;

import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseView;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface PopularTopicsView extends BaseView {
    void setClassifyAdapter(FirstCategoryAdapter adapter);

    void setBlogListAdapter(RecentlyBlogAdapter adapter);

    void onFirstItemClick(ClassifyEntity entity);

    void onBlogItemClick(RecentlyBlogInfoEntity entity);
}
