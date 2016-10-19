package com.android.sjq.wanandroid02.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseFragment;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.presenters.PopularTopicsPresenter;
import com.android.sjq.wanandroid02.views.activities.BlogDetailActivity;
import com.android.sjq.wanandroid02.views.view.PopularTopicsView;


//热门专题Fragment
public class PopularTopicsFragment extends BaseFragment<PopularTopicsView, PopularTopicsPresenter> implements PopularTopicsView {
    RecyclerView first_category;
    RecyclerView blog_rv;

    public PopularTopicsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PopularTopicsFragment newInstance() {
        PopularTopicsFragment fragment = new PopularTopicsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_popular_topics, container, false);
        initView(view);
        //获取数据
        mPresenter.getClassifyData();
        return view;
    }

    //初始化界面
    private void initView(View view) {
        first_category = (RecyclerView) view.findViewById(R.id.first_category);
        first_category.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        blog_rv = (RecyclerView) view.findViewById(R.id.blog_rv);
        blog_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected PopularTopicsPresenter initPresenter() {
        return new PopularTopicsPresenter();
    }


    @Override
    public void setClassifyAdapter(FirstCategoryAdapter adapter) {
        first_category.setAdapter(adapter);
    }

    @Override
    public void setBlogListAdapter(RecentlyBlogAdapter adapter) {
        blog_rv.setAdapter(adapter);
    }

    @Override
    public void onFirstItemClick(ClassifyEntity entity) {
        mPresenter.getBlogData(entity);
    }

    @Override
    public void onBlogItemClick(RecentlyBlogInfoEntity entity) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url",entity.getBlogaddress());
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
