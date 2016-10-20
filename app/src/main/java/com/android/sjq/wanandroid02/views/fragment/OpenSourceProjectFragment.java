package com.android.sjq.wanandroid02.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.adapters.OpenExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BaseFragment;
import com.android.sjq.wanandroid02.presenters.OpenSourcePresenter;
import com.android.sjq.wanandroid02.views.activities.BlogDetailActivity;
import com.android.sjq.wanandroid02.views.view.OpenSourceView;


//开源项目Fragment
public class OpenSourceProjectFragment extends BaseFragment<OpenSourceView, OpenSourcePresenter> implements OpenSourceView {
    ExpandableListView category_lv;
    RecyclerView blogs_lv;


    public OpenSourceProjectFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OpenSourceProjectFragment newInstance() {
        OpenSourceProjectFragment fragment = new OpenSourceProjectFragment();
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

        View view = inflater.inflate(R.layout.fragment_open_source_project, container, false);
        initView(view);
        //获取数据
        mPresenter.getClassifyData();
        // Inflate the layout for this fragment
        return view;
    }

    //初始化界面
    private void initView(View view) {
        category_lv = (ExpandableListView) view.findViewById(R.id.open_category_lv);
        blogs_lv = (RecyclerView) view.findViewById(R.id.open_blog_rl);
        blogs_lv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        category_lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mPresenter.onGroupItemClickListener(groupPosition, childPosition);
                return false;
            }
        });


    }

    @Override
    protected OpenSourcePresenter initPresenter() {
        return new OpenSourcePresenter();
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
    public void setOpenSourceAdapter(OpenExpandableAdapter adapter) {
        category_lv.setAdapter(adapter);
    }

    @Override
    public void setBlogLvAdapter(RecentlyBlogAdapter adapter) {
        blogs_lv.setAdapter(adapter);
    }

    @Override
    public void onBlogItemClick(String url) {
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
