package com.android.sjq.wanandroid02.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseFragment;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.presenters.KnowledgePresenter;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.activities.BlogDetailActivity;
import com.android.sjq.wanandroid02.views.activities.MainActivity;
import com.android.sjq.wanandroid02.views.view.KnowledgeView;

//知识体系Fragment
public class KnowledgeSystemFragment extends BaseFragment<KnowledgeView, KnowledgePresenter> implements ExpandableListView.OnChildClickListener, KnowledgeView {

    private ExpandableListView expandable_lv;
    private RecyclerView know_rl;

    public KnowledgeSystemFragment() {
    }

    public static KnowledgeSystemFragment newInstance() {
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
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
        View view = inflater.inflate(R.layout.fragment_knowledge_system, container, false);
        initView(view);
        ((MainActivity) getActivity()).showDialog();
        mPresenter.initData();
        return view;
    }

    @Override
    protected KnowledgePresenter initPresenter() {
        return new KnowledgePresenter();
    }

    /**
     * 初始化界面
     */
    private void initView(View view) {
        expandable_lv = (ExpandableListView) view.findViewById(R.id.expandable_lv);
        expandable_lv.setGroupIndicator(null);
        expandable_lv.setOnChildClickListener(this);
        know_rl = (RecyclerView) view.findViewById(R.id.know_rl);
        know_rl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ((MainActivity) getActivity()).showDialog();
        mPresenter.getBlogList(groupPosition, childPosition);
        return true;
    }

    @Override
    public void setExpandableAdapter(ExpandableListAdapter adapter) {
        expandable_lv.setAdapter(adapter);
        //设置第一项为展开
        expandable_lv.expandGroup(0);
    }

    @Override
    public void setBlogListAdapter(RecentlyBlogAdapter adapter) {
        ((MainActivity) getActivity()).dissmissDialog();
        know_rl.setAdapter(adapter);
    }

    @Override
    public void onBlogClick(RecentlyBlogInfoEntity entity) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", entity.getBlogaddress());
        Log.print("url", entity.getBlogaddress());
        startActivity(intent);
    }
}
