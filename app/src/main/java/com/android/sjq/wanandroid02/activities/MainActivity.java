package com.android.sjq.wanandroid02.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.adapters.ClassifyAdapter;
import com.android.sjq.wanandroid02.base.BaseMvpActivity;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.presenters.MainPresenter;
import com.android.sjq.wanandroid02.views.MainView;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView {
    private RecyclerView classify_rv;
    private FrameLayout frameLayout;
    private String[] flags = {"HomeFragment", "knowledgeSystemFragment", "popularTopicsFragment", "frameworkFragment", "newTechnologyFragment", "openSourceProjectFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //获取数据
        mPresenter.getClassify();
    }


    private void initView() {
        classify_rv = (RecyclerView) findViewById(R.id.classify_rv);
        classify_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        frameLayout = (FrameLayout) findViewById(R.id.content_layout);
    }


    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    /**
     * 设置界面数据
     *
     * @param list
     */
    @Override
    public void setClassify(ArrayList<ClassifyEntity> list) {
        ClassifyAdapter adapter = new ClassifyAdapter(MainActivity.this, list);
        //adapter.setOnItemClickListener(MainActivity.this);
        classify_rv.setAdapter(adapter);
    }
}
