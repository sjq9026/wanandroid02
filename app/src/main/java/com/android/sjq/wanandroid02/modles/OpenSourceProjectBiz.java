package com.android.sjq.wanandroid02.modles;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.OpenExpandableAdapter;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28.
 */

public class OpenSourceProjectBiz {
    private ArrayList<LeaderOrCategoryEntity> leaderEntitiesl;
    private ArrayList<RecentlyBlogInfoEntity> blogs;

    public void getClassifyData(final onDataResult callback) {
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
                callback.onSuccess(adapter);
            }
        }.execute();
    }

    public void getBlogData(final String url, final onDataResult callback) {

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
                callback.onSuccess(adapter);
            }
        }.execute();
    }




    public ArrayList<LeaderOrCategoryEntity> getLeaderEntitiesl() {
        return leaderEntitiesl;
    }



    public ArrayList<RecentlyBlogInfoEntity> getBlogs() {
        return blogs;
    }


}
