package com.android.sjq.wanandroid02.presenters;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnGroupItemClickListener;
import com.android.sjq.wanandroid02.adapters.OpenExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.LeaderOrCategoryEntity;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.modles.SubClassEntity;
import com.android.sjq.wanandroid02.views.view.OpenSourceView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */

public class OpenSourcePresenter extends BasePresenter<OpenSourceView> implements OnGroupItemClickListener, OnBlogItemClickListener {
    private ArrayList<LeaderOrCategoryEntity> leaderEntitiesl;
    private ArrayList<RecentlyBlogInfoEntity> blogs;

    public void getClassifyData() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    leaderEntitiesl = new ArrayList<LeaderOrCategoryEntity>();
                    Document doc = Jsoup.connect(Contacts.OPEN_SOURCE_URL).get();
                    LeaderOrCategoryEntity entity = null;
                    SubClassEntity subClassEntity = null;
                    ArrayList<SubClassEntity> subClassEntities = null;
                    Elements es = doc.getElementsByClass("nav").get(0).select("li");
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        entity = new LeaderOrCategoryEntity();

                        //System.out.println(e.toString()+"\n\n");
                        Elements es1 = e.getElementsByTag("a");
                        if (es1.size() > 1) {
                            System.out.println(e.select("a").get(0).text());
                            entity.setLeaderName(e.select("a").get(0).text());
                            subClassEntities = new ArrayList<SubClassEntity>();
                            for (int j = 1; j < es1.size(); j++) {
                                Element ee = es1.get(j);
                                subClassEntity = new SubClassEntity();
                                subClassEntity.setSubClassName(ee.select("a").text());
                                subClassEntity.setSubClassUrl(Contacts.BASE_URL + ee.select("a").attr("href"));
                                // System.out.println("   " + ee.select("a").text());
                                subClassEntities.add(subClassEntity);
                            }
                            entity.setSubClassEntities(subClassEntities);
                            leaderEntitiesl.add(entity);
                            i += (es1.size() - 1);
                        } else {
                            entity.setLeaderName(e.select("a").text());
                            entity.setCategoryUrl(Contacts.BASE_URL + e.select("a").attr("href"));
                            leaderEntitiesl.add(entity);
                            //System.out.println(e.select("a").text());
                        }
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "OK";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                OpenExpandableAdapter adapter = new OpenExpandableAdapter(leaderEntitiesl, TApplication.mContext);
                view.setOpenSourceAdapter(adapter);
                adapter.setOnGroupItemClickListener(OpenSourcePresenter.this);
                String url = "";
                if (leaderEntitiesl.get(0).getSubClassEntities() != null && leaderEntitiesl.get(0).getSubClassEntities().size() > 0) {
                    url = leaderEntitiesl.get(0).getSubClassEntities().get(0).getSubClassUrl();
                } else {
                    url = leaderEntitiesl.get(0).getCategoryUrl();
                }
                //获取博文列表
                getBlogData(url);
            }
        }.execute();
    }


    public void getBlogData(final String url) {

        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    blogs = new ArrayList<RecentlyBlogInfoEntity>();
                    Document doc = Jsoup.connect(url).get();
                    RecentlyBlogInfoEntity blog = null;
                    Elements es = doc.getElementsByClass("article_div");
                    for (Element e : es) {
                        blog = new RecentlyBlogInfoEntity();
                        String name = e.select("a").text();
                        String athor = e.select("span").get(0).text();
                        String source = e.select("span").get(1).text();
                        String date = e.select("span").get(2).text();
                        blog.setDate(date);
                        blog.setSource(source);
                        blog.setAuthor(athor);
                        blog.setBlogaddress(e.select("a").attr("href"));
                        blog.setBlogname(name);
                        blogs.add(blog);
                        System.out.println(name + "\n" + athor + "\n" + source + "\n" + date + "\n");
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "OK";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                RecentlyBlogAdapter adapter = new RecentlyBlogAdapter(TApplication.mContext, blogs);
                view.setBlogLvAdapter(adapter);
                adapter.setOnItemClickListener(OpenSourcePresenter.this);
            }
        }.execute();
    }

    /**
     * 当childPosition = -1时，说明gruopItem被点击，当childPosition！=-1时，childItem被点击
     *
     * @param groupPosition group被点击
     * @param childPosition
     */
    @Override
    public void onGroupItemClickListener(int groupPosition, int childPosition) {
        String url = "";
        //childItem被点击
        if (childPosition != -1) {
            url = leaderEntitiesl.get(groupPosition).getSubClassEntities().get(childPosition).getSubClassUrl();
            //groupItem被点击
        } else {
            url = leaderEntitiesl.get(groupPosition).getCategoryUrl();
        }
        getBlogData(url);
    }



    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogItemClick(blogs.get(position).getBlogaddress());
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
