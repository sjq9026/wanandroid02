package com.android.sjq.wanandroid02.presenters;

import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnGroupItemClickListener;
import com.android.sjq.wanandroid02.adapters.OpenExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.OpenSourceProjectBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.OpenSourceView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class OpenSourcePresenter extends BasePresenter<OpenSourceView> implements OnGroupItemClickListener, OnBlogItemClickListener {
    OpenSourceProjectBiz mBiz;

    public OpenSourcePresenter() {
        mBiz = new OpenSourceProjectBiz();
    }

    public void getClassifyData() {
        mBiz.getClassifyData(new onDataResult<OpenExpandableAdapter>() {
            @Override
            public void onSuccess(OpenExpandableAdapter adapter) {
                view.setOpenSourceAdapter(adapter);
                adapter.setOnGroupItemClickListener(OpenSourcePresenter.this);
                String url = "";
                if (mBiz.getLeaderEntitiesl().get(0).getSubClassEntities() != null && mBiz.getLeaderEntitiesl().get(0).getSubClassEntities().size() > 0) {
                    url = mBiz.getLeaderEntitiesl().get(0).getSubClassEntities().get(0).getSubClassUrl();
                } else {
                    url = mBiz.getLeaderEntitiesl().get(0).getCategoryUrl();
                }
                //获取博文列表
                getBlogData(url);
            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }

    public void getBlogData(final String url) {
        mBiz.getBlogData(url, new onDataResult<RecentlyBlogAdapter>() {
            @Override
            public void onSuccess(RecentlyBlogAdapter adapter) {
                //设置adapter
                view.setBlogLvAdapter(adapter);
                adapter.setOnItemClickListener(OpenSourcePresenter.this);
            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    /**
     * 当childPosition = -1时，说明gruopItem被点击，当childPosition！=-1时，childItem被点击
     *
     * @param groupPosition group被点击
     * @param childPosition
     */
    @Override
    public void onGroupItemClickListener(int groupPosition, int childPosition) {
        String url = "";
        //childItem被点击
        if (childPosition != -1) {
            url = mBiz.getLeaderEntitiesl().get(groupPosition).getSubClassEntities().get(childPosition).getSubClassUrl();
            //groupItem被点击
        } else {
            url = mBiz.getLeaderEntitiesl().get(groupPosition).getCategoryUrl();
        }
        getBlogData(url);
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogItemClick(mBiz.getBlogs().get(position).getBlogaddress());
    }

    @Override
    public void onClassifyNameClickListener(int position) {
    }
}
