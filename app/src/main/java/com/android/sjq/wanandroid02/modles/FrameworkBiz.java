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

public class FrameworkBiz {
    private ArrayList<ClassifyEntity> mClassifies;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlies;

    public void getCategoryData(final onDataResult<FirstCategoryAdapter> callback) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                try {
                    Document doc = Jsoup.connect(Contacts.FRAGMENT_WORK_URL).get();
                    Elements es = doc.getElementsByClass("nav").get(0).getElementsByTag("a");
                    ClassifyEntity entity = null;
                    mClassifies = new ArrayList<ClassifyEntity>();
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        entity = new ClassifyEntity();
                        entity.setClassifyName(e.select("a").text());
                        entity.setHttp_url(Contacts.BASE_URL + e.select("a").attr("href"));
                        mClassifies.add(entity);
                        System.out.println(e.toString() + "\n\n");
                    }
                    result = "OK";
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = "ERR";
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("OK")) {
                    FirstCategoryAdapter adapter = new FirstCategoryAdapter(TApplication.mContext, mClassifies);
                    callback.onSuccess(adapter);
// adapter.setOnItemClickListener(FrameworkPresenter.this);
//                    view.setCategoryAdapter(adapter);
//                    getBlogData(mClassifies.get(0));
                } else {
                    // view.showShorToast("数据请求失败！！！");
                    return;
                }
            }
        }.execute();
    }


    public void getBlogData(final ClassifyEntity entity, final onDataResult callback) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                mRecentlies = new ArrayList<RecentlyBlogInfoEntity>();
                try {
                    Document doc = Jsoup.connect(entity.getHttp_url()).get();
                    Elements es = doc.getElementsByClass("article_div");
                    RecentlyBlogInfoEntity entity = null;
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        entity = new RecentlyBlogInfoEntity();
                        String name = e.select("a").text();
                        String addr = e.select("a").attr("href");
                        String auth = e.select("span").get(0).text();
                        String source = e.select("span").get(1).text();
                        String date = e.select("span").get(2).text();
                        entity.setBlogname(name);
                        entity.setBlogaddress(addr);
                        entity.setAuthor(auth);
                        entity.setSource(source);
                        entity.setDate(date);
                        mRecentlies.add(entity);
//                        System.out.println(name + "\n");
//                        System.out.println(addr + "\n");
//                        System.out.println(auth + "\n");
//                        System.out.println(source + "\n");
//                        System.out.println(name + "\n");
                    }
                    result = "OK";
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = "ERR";
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("OK")) {
                    RecentlyBlogAdapter adapter = new RecentlyBlogAdapter(TApplication.mContext, mRecentlies);
                    callback.onSuccess(adapter);
                    //adapter.setOnItemClickListener(FrameworkPresenter.this);
                    //view.setBlogAdapter(adapter);
                } else {
                    //view.showShorToast("数据请求失败！！！");
                    return;
                }
            }
        }.execute();
    }

    public ArrayList<ClassifyEntity> getmClassifies() {
        return mClassifies;
    }


    public ArrayList<RecentlyBlogInfoEntity> getmRecentlies() {
        return mRecentlies;
    }

}
