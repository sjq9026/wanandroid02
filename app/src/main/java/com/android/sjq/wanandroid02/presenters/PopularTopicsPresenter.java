package com.android.sjq.wanandroid02.presenters;

import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnClassifyItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.PopularTopicsBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.view.PopularTopicsView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class PopularTopicsPresenter extends BasePresenter<PopularTopicsView> implements OnBlogItemClickListener, OnClassifyItemClickListener {
    PopularTopicsBiz mBiz;

    public PopularTopicsPresenter() {
        mBiz = new PopularTopicsBiz();
    }

    public void getClassifyData() {
        mBiz.getClassifyData(new onDataResult<FirstCategoryAdapter>() {
            @Override
            public void onSuccess(FirstCategoryAdapter adapter) {
                //设置界面adapter
                view.setClassifyAdapter(adapter);
                //获取第一个一级分类下的blog列表
                getBlogData(mBiz.getmClassifies().get(0));
                adapter.setOnItemClickListener(PopularTopicsPresenter.this);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void getBlogData(final ClassifyEntity entity) {
        mBiz.getBlogData(entity, new onDataResult<RecentlyBlogAdapter>() {
            @Override
            public void onSuccess(RecentlyBlogAdapter adapter) {
                //设置博文列表界面
                view.setBlogListAdapter(adapter);
                //设置点击监听
                adapter.setOnItemClickListener(PopularTopicsPresenter.this);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }


    @Override
    public void onItemClick(ClassifyEntity entity, RecyclerView.ViewHolder viewHolder) {
        Log.print("entity", entity.toString());
        view.onFirstItemClick(entity);
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogItemClick(mBiz.getmRecentlies().get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
