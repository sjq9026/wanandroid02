package com.android.sjq.wanandroid02.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.adapters.ClassifyAdapter;
import com.android.sjq.wanandroid02.adapters.OnClassifyItemClickListener;
import com.android.sjq.wanandroid02.base.BaseMvpActivity;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.presenters.MainPresenter;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.fragment.FrameworkFragment;
import com.android.sjq.wanandroid02.views.fragment.HomeFragment;
import com.android.sjq.wanandroid02.views.fragment.KnowledgeSystemFragment;
import com.android.sjq.wanandroid02.views.fragment.NewTechnologyFragment;
import com.android.sjq.wanandroid02.views.fragment.OpenSourceProjectFragment;
import com.android.sjq.wanandroid02.views.fragment.PopularTopicsFragment;
import com.android.sjq.wanandroid02.views.view.MainView;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView, OnClassifyItemClickListener {
    private RecyclerView classify_rv;
    private FrameLayout frameLayout;
    private String[] flags = {"HomeFragment", "knowledgeSystemFragment", "popularTopicsFragment", "frameworkFragment", "newTechnologyFragment", "openSourceProjectFragment"};
    private Fragment currentFragment;
    private HomeFragment homeFragment;
    private KnowledgeSystemFragment knowledgeSystemFragment;
    private PopularTopicsFragment popularTopicsFragment;
    private FrameworkFragment frameworkFragment;
    private NewTechnologyFragment newTechnologyFragment;
    private OpenSourceProjectFragment openSourceProjectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //获取数据
        mPresenter.getClassify();
        //设置fragment
        setDefaultFragment(savedInstanceState);
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
     * @param adapter
     */
    @Override
    public void setClassify(ClassifyAdapter adapter) {
        adapter.setOnItemClickListener(this);
        classify_rv.setAdapter(adapter);
    }



    public void setDefaultFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance();
            if (!homeFragment.isAdded()) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.content_layout, homeFragment, flags[0]);
                transaction.commit();
            }
            currentFragment = homeFragment;
        }
    }


    @Override
    public void onItemClick(ClassifyEntity entity, RecyclerView.ViewHolder viewHolder) {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        switch (entity.getClassifyName()) {
            case Contacts.HOME:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                }
                FragmentTransaction tran = manager.beginTransaction();
                tran.hide(currentFragment);
                manager.executePendingTransactions();
                if (!homeFragment.isAdded()) {
                    tran.add(R.id.content_layout, homeFragment, flags[0]);
                }
                tran.show(homeFragment);
                currentFragment = homeFragment;
                tran.commit();
                Log.print("Click", Contacts.HOME);
                break;
            case Contacts.KNOWLEDGE_SYSTEM:
                if (knowledgeSystemFragment == null) {
                    knowledgeSystemFragment = KnowledgeSystemFragment.newInstance();
                }
                FragmentTransaction tran1 = manager.beginTransaction();
                tran1.hide(currentFragment);
                manager.executePendingTransactions();
                if (!knowledgeSystemFragment.isAdded()) {
                    tran1.add(R.id.content_layout, knowledgeSystemFragment, flags[1]);
                }
                tran1.show(knowledgeSystemFragment);
                currentFragment = knowledgeSystemFragment;
                tran1.commit();
                Log.print("Click", Contacts.KNOWLEDGE_SYSTEM);
                break;
            case Contacts.POPULAR_TOPICS:
                if (popularTopicsFragment == null) {
                    popularTopicsFragment = PopularTopicsFragment.newInstance();
                }
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.hide(currentFragment);
                manager.executePendingTransactions();
                if (!popularTopicsFragment.isAdded()) {
                    transaction2.add(R.id.content_layout, popularTopicsFragment, flags[2]);
                }
                currentFragment = popularTopicsFragment;
                transaction2.show(popularTopicsFragment);
                transaction2.commit();
                Log.print("Click", Contacts.POPULAR_TOPICS);
                break;
            case Contacts.COMMON_FRAMEWORK_FOR_PROJECTS:
                if (frameworkFragment == null) {
                    frameworkFragment = FrameworkFragment.newInstance();
                }
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.hide(currentFragment);
                if (!frameworkFragment.isAdded()) {
                    transaction3.add(R.id.content_layout, frameworkFragment, flags[3]);
                }
                manager.executePendingTransactions();
                currentFragment = frameworkFragment;
                transaction3.show(frameworkFragment);
                transaction3.commit();
                Log.print("Click", Contacts.COMMON_FRAMEWORK_FOR_PROJECTS);
                break;
            case Contacts.HIGH_TECH:
                if (newTechnologyFragment == null) {
                    newTechnologyFragment = NewTechnologyFragment.newInstance();
                }
                FragmentTransaction transaction4 = manager.beginTransaction();
                transaction4.hide(currentFragment);
                if (!newTechnologyFragment.isAdded()) {
                    transaction4.add(R.id.content_layout, newTechnologyFragment, flags[4]);
                }
                manager.executePendingTransactions();
                currentFragment = newTechnologyFragment;
                transaction4.show(newTechnologyFragment);
                transaction4.commit();
                Log.print("Click", Contacts.HIGH_TECH);
                break;
            case Contacts.OPEN_SOURCE_PROJECT_CATEGORY:
                if (openSourceProjectFragment == null) {
                    openSourceProjectFragment = openSourceProjectFragment.newInstance();
                }
                FragmentTransaction transaction5 = manager.beginTransaction();
                transaction5.hide(currentFragment);
                if (!openSourceProjectFragment.isAdded()) {
                    transaction5.add(R.id.content_layout, openSourceProjectFragment, flags[5]);
                }
                manager.executePendingTransactions();
                currentFragment = openSourceProjectFragment;
                transaction5.show(openSourceProjectFragment);
                transaction5.commit();
                Log.print("Click", Contacts.OPEN_SOURCE_PROJECT_CATEGORY);
                break;
        }
    }
}
