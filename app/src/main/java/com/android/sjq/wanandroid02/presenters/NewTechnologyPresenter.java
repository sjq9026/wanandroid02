package com.android.sjq.wanandroid02.presenters;

import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnClassifyItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.NewTechnologyBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.NewTechnologyView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class NewTechnologyPresenter extends BasePresenter<NewTechnologyView> implements OnClassifyItemClickListener, OnBlogItemClickListener {
    NewTechnologyBiz mBiz;

    public NewTechnologyPresenter() {
        mBiz = new NewTechnologyBiz();
    }

    /**
     * 获取一级分类数据
     */
    public void initCategoryData() {
        mBiz.initCategoryData(new onDataResult<FirstCategoryAdapter>() {
            @Override
            public void onSuccess(FirstCategoryAdapter adapter) {
                view.setCategoryAdapter(adapter);
                adapter.setOnItemClickListener(NewTechnologyPresenter.this);
                getBlogData(mBiz.getmClassifies().get(0));
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 获取博文列表数据
     * @param entity
     */
    public void getBlogData(final ClassifyEntity entity) {
        mBiz.getBlogData(entity, new onDataResult<RecentlyBlogAdapter>() {
            @Override
            public void onSuccess(RecentlyBlogAdapter adapter) {
                view.setBlogAdapter(adapter);
                adapter.setOnItemClickListener(NewTechnologyPresenter.this);
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
