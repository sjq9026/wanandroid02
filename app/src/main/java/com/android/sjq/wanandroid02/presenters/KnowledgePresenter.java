package com.android.sjq.wanandroid02.presenters;

import android.os.AsyncTask;
import android.widget.Toast;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.ExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.LeaderEntity;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.modles.SubClassEntity;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.view.KnowledgeView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeView> implements OnBlogItemClickListener {
    public ArrayList<LeaderEntity> leaderEntities;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlyBlogs;

    /**
     * 初始化data
     */
    public void initData() {
        new AsyncTask<String, Integer, String>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                Document document;
                try {
                    //一级菜单
                    leaderEntities = new ArrayList<LeaderEntity>();
                    //二级菜单
                    ArrayList<SubClassEntity> subClassEntities = null;
                    //二级菜单对象
                    SubClassEntity subClassEntity = null;
                    document = Jsoup.connect(Contacts.KNOW_SYSTEM_BASE_URL).get();
                    Elements elements1 = document.getElementsByClass("left");
                    for (int i = 0; i < elements1.size(); i++) {
                        Element es = elements1.get(i);
                        Elements alles = es.getElementsByTag("ul");
                        Element e = alles.get(0);
                        //获取所有的a标签
                        Elements subUls = e.getElementsByTag("a");
                        LeaderEntity entity = null;
                        int leaderSum = -1;
                        //遍历a标签
                        for (int j = 0; j < subUls.size(); j++) {
                            Element ule = subUls.get(j);
                            //如果当前a标签是一级标签
                            if (ule.attr("class").equals("leader")) {
                                entity = new LeaderEntity();
                                entity.setLeaderName(ule.text());
                                leaderEntities.add(entity);
                                subClassEntities = null;
                                leaderSum++;
                                //  System.out.println(ule.text());
                                //如果是二级标签，创建二级标签集合，将当前一级标签下所有二级标签添加到集合中
                            } else {
                                if (subClassEntities == null) {
                                    subClassEntities = new ArrayList<SubClassEntity>();
                                }
                                subClassEntity = new SubClassEntity();
                                subClassEntity.setSubClassName(ule.text());
                                subClassEntity.setSubClassUrl(Contacts.BASE_URL + ule.attr("href"));
                                subClassEntities.add(subClassEntity);
                                entity.setSubClassEntities(subClassEntities);
                                // Log.print("sub",leaderEntities.get(leaderSum).toString());
                                //  System.out.println("      " + ule.text());
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.print("sub", leaderEntities.size() + "");
                return "OK";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals("OK")) {
                    ExpandableAdapter adapter = new ExpandableAdapter(leaderEntities, TApplication.mContext);
                    view.setExpandableAdapter(adapter);
                    //获取当前分类的二级列表
                    getBlogList(0,0);
                } else {
                    Toast.makeText(TApplication.mContext, "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    /**
     * 获取当前二级分类下的列表
     * @param groupPosition
     * @param childPosition
     */
    public void getBlogList(int groupPosition,int childPosition) {
        final SubClassEntity subClassEntity =
                leaderEntities.get(groupPosition).getSubClassEntities().get(childPosition);
        new AsyncTask<String, Integer, String>() {
            String url = Contacts.BASE_URL + subClassEntity.getSubClassUrl();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.print("url", url);
            }

            @Override
            protected String doInBackground(String... params) {
                Document document = null;
                mRecentlyBlogs = new ArrayList<RecentlyBlogInfoEntity>();
                try {
                    document = Jsoup.connect(url).get();
                    Elements es = document.getElementsByClass("article_div");
                    RecentlyBlogInfoEntity entity = null;
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        entity = new RecentlyBlogInfoEntity();
//                        System.out.println(e.select("a").text() + ":" + e.select("a").attr("href"));
//                        System.out.println(e.select("span").get(0).text());
//                        System.out.println(e.select("span").get(1).text());
//                        System.out.println(e.select("span").get(2).text() + "\n");
                        entity.setBlogname(e.select("a").text());
                        entity.setBlogaddress(e.select("a").attr("href"));
                        entity.setAuthor(e.select("span").get(0).text());
                        entity.setSource(e.select("span").get(1).text());
                        entity.setDate(e.select("span").get(2).text());
                        entity.setClassify(null);
                        entity.setClassifyaddress(null);
                        mRecentlyBlogs.add(entity);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.print("data", mRecentlyBlogs.toString());
                return "OK";
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                RecentlyBlogAdapter adapter =
                        new RecentlyBlogAdapter(TApplication.mContext, mRecentlyBlogs);
                adapter.setOnItemClickListener(KnowledgePresenter.this);
                view.setBlogListAdapter(adapter);
            }
        }.execute();
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogClick(mRecentlyBlogs.get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
