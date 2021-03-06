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
import com.android.sjq.wanandroid02.presenters.FrameworkPresenter;
import com.android.sjq.wanandroid02.views.activities.BlogDetailActivity;
import com.android.sjq.wanandroid02.views.activities.MainActivity;
import com.android.sjq.wanandroid02.views.view.FrameworkView;


//项目常用框架Fragment
public class FrameworkFragment extends BaseFragment<FrameworkView, FrameworkPresenter> implements FrameworkView {
    RecyclerView first_category;
    RecyclerView blog_rv;

    public FrameworkFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FrameworkFragment newInstance() {
        FrameworkFragment fragment = new FrameworkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_framework, container, false);
        // Inflate the layout for this fragment
        initView(view);
        ((MainActivity) getActivity()).showDialog();
        //加载数据
        mPresenter.initCategoryData();
        return view;
    }

    @Override
    protected FrameworkPresenter initPresenter() {
        return new FrameworkPresenter();
    }

    //初始化界面
    private void initView(View view) {
        first_category = (RecyclerView) view.findViewById(R.id.first_category);
        first_category.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        blog_rv = (RecyclerView) view.findViewById(R.id.blog_rv);
        blog_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setCategoryAdapter(FirstCategoryAdapter adapter) {
        first_category.setAdapter(adapter);
    }

    @Override
    public void setBlogAdapter(RecentlyBlogAdapter adapter) {
        ((MainActivity) getActivity()).dissmissDialog();
        blog_rv.setAdapter(adapter);
    }

    @Override
    public void onCategoryClick(ClassifyEntity entity) {
        ((MainActivity) getActivity()).showDialog();
        mPresenter.getBlogData(entity);
    }

    @Override
    public void onBlogClick(RecentlyBlogInfoEntity entity) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", entity.getBlogaddress());
        startActivity(intent);
    }
}
