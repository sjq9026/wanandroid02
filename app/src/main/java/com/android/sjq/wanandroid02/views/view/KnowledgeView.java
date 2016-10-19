package com.android.sjq.wanandroid02.views.view;

import android.widget.ExpandableListAdapter;

import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseView;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;

/**
 * Created by Administrator on 2016/10/19.
 */

public interface KnowledgeView extends BaseView {
    void setExpandableAdapter(ExpandableListAdapter adapter);

    void setBlogListAdapter(RecentlyBlogAdapter adapter);

    void onBlogClick(RecentlyBlogInfoEntity entity);
}
