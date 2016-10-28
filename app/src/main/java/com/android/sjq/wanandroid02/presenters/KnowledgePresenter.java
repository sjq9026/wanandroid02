package com.android.sjq.wanandroid02.presenters;

import com.android.sjq.wanandroid02.adapters.ExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.KnowledgeBiz;
import com.android.sjq.wanandroid02.modles.onDataResult;
import com.android.sjq.wanandroid02.views.view.KnowledgeView;

/**
 * Created by Administrator on 2016/10/19.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeView> implements OnBlogItemClickListener {
    private KnowledgeBiz mBiz;

    public KnowledgePresenter() {
        mBiz = new KnowledgeBiz();
    }

    /**
     * 初始化分组数据
     */
    public void initData() {
        mBiz.getLeaderData(new onDataResult<ExpandableAdapter>() {
            @Override
            public void onSuccess(ExpandableAdapter adapter) {
                //设置分类adapter
                view.setExpandableAdapter(adapter);
                //获取分类的第一个子项文章列表
                getBlogList(0, 0);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    //获取文章列表回调
    onDataResult<RecentlyBlogAdapter> callback = new onDataResult<RecentlyBlogAdapter>() {
        @Override
        public void onSuccess(RecentlyBlogAdapter adapter) {
            view.setBlogListAdapter(adapter);
        }

        @Override
        public void onFailure(String msg) {

        }
    };


    /**
     * 获取当前二级分类下的列表
     */
    public void getBlogList(int groupPosition, int childPosition) {
        mBiz.getBlogList(mBiz.getLeaderEntityList().get(groupPosition).getSubClassEntities().get(childPosition), callback);
    }


    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogClick(mBiz.getRecentlyBlogList().get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
