package com.android.sjq.wanandroid02.modles;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
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

public class PopularTopicsBiz {
    private ArrayList<ClassifyEntity> mClassifies;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlies;

    public void getClassifyData(final onDataResult callback) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    mClassifies = new ArrayList<ClassifyEntity>();
                    ClassifyEntity entity = null;
                    Document doc = Jsoup.connect(Contacts.POPULAR_TOPICS_URL).get();
                    Elements es = doc.getElementsByClass("nav");
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        Elements es1 = e.select("a");
                        for (int j = 0; j < es1.size(); j++) {
                            entity = new ClassifyEntity();
                            Element el = es1.get(j);
                            String name = el.select("a").text();
                            String href = el.select("a").attr("href");
                            entity.setClassifyName(name);
                            entity.setHttp_url(Contacts.BASE_URL + href);
                            mClassifies.add(entity);
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
                FirstCategoryAdapter adapter = new FirstCategoryAdapter(TApplication.mContext, mClassifies);
                callback.onSuccess(adapter);
//                adapter.setOnItemClickListener(PopularTopicsPresenter.this);
//                view.setClassifyAdapter(adapter);
//                getBlogData(mClassifies.get(0));
            }
        }.execute();
    }

    public void getBlogData(final ClassifyEntity entity, final onDataResult callback) {
        new AsyncTask<String, Integer, String>() {
            String url = entity.getHttp_url();

            @Override
            protected String doInBackground(String... params) {
                mRecentlies = new ArrayList<RecentlyBlogInfoEntity>();
                RecentlyBlogInfoEntity entity = null;
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements es = doc.getElementsByClass("article_div");
                    for (int i = 0; i < es.size(); i++) {
                        entity = new RecentlyBlogInfoEntity();
                        Element e = es.get(i);
                        String name = e.select("a").text();
                        String url1 = e.select("a").attr("href");
                        String str1 = e.getElementsByTag("span").get(0).text();
                        String str2 = e.getElementsByTag("span").get(1).text();
                        String str3 = e.getElementsByTag("span").get(2).text();
                        entity.setBlogaddress(url1);
                        entity.setBlogname(name);
                        entity.setAuthor(str1);
                        entity.setSource(str2);
                        entity.setDate(str3);
                        mRecentlies.add(entity);
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
                RecentlyBlogAdapter adapter = new RecentlyBlogAdapter(TApplication.mContext, mRecentlies);
                callback.onSuccess(adapter);
            }
        }.execute();
    }


    public ArrayList<ClassifyEntity> getmClassifies() {
        if (mClassifies != null) {
            return mClassifies;
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<RecentlyBlogInfoEntity> getmRecentlies(){
        if (mRecentlies != null) {
            return mRecentlies;
        } else {
            return new ArrayList<>();
        }
    }

}
