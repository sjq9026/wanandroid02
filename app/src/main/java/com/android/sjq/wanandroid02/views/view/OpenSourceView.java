package com.android.sjq.wanandroid02.views.view;

import com.android.sjq.wanandroid02.adapters.OpenExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseView;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface OpenSourceView extends BaseView {
    void setOpenSourceAdapter(OpenExpandableAdapter adapter);

    void setBlogLvAdapter(RecentlyBlogAdapter adapter);

    void onBlogItemClick(String url);
}
