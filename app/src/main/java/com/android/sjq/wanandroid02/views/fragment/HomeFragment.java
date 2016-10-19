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
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseFragment;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.presenters.HomePresenter;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.activities.BlogDetailActivity;
import com.android.sjq.wanandroid02.views.view.HomeView;


//首页Fragment
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    private RecyclerView recently_blog_rv;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        //获取最近热门信息数据
        mPresenter.initRecentlyData();

        return view;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView(View view) {
        recently_blog_rv = (RecyclerView) view.findViewById(R.id.recently_blog_rv);
        recently_blog_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setBlogSuc(RecentlyBlogAdapter adapter) {
        recently_blog_rv.setAdapter(adapter);
    }

    @Override
    public void onBlogNameClick(RecentlyBlogInfoEntity entity) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", entity.getBlogaddress());
        Log.print("url", entity.getBlogaddress());
        startActivity(intent);
    }

    @Override
    public void onBlogClassifyClick(RecentlyBlogInfoEntity entity) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", entity.getClassifyaddress());
        Log.print("url", entity.getBlogaddress());
        startActivity(intent);
    }
}
