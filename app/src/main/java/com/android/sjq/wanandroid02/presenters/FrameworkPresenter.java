package com.android.sjq.wanandroid02.presenters;

import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnClassifyItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.FrameworkBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.FrameworkView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class FrameworkPresenter extends BasePresenter<FrameworkView> implements OnClassifyItemClickListener, OnBlogItemClickListener {
    FrameworkBiz mBiz;

    public FrameworkPresenter() {
        mBiz = new FrameworkBiz();
    }

    public void initCategoryData() {
        mBiz.getCategoryData(new onDataResult<FirstCategoryAdapter>() {
            @Override
            public void onSuccess(FirstCategoryAdapter adapter) {
                //设置一级列表adapter
                view.setCategoryAdapter(adapter);
                //设置监听事件
                adapter.setOnItemClickListener(FrameworkPresenter.this);
                //获取第一个分类的数据
                getBlogData(mBiz.getmClassifies().get(0));
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
                view.setBlogAdapter(adapter);
                adapter.setOnItemClickListener(FrameworkPresenter.this);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }


    @Override
    public void onItemClick(ClassifyEntity entity, RecyclerView.ViewHolder viewHolder) {
        view.onCategoryClick(entity);
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogClick(mBiz.getmRecentlies().get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
