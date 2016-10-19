package com.android.sjq.wanandroid02.presenters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.FirstCategoryAdapter;
import com.android.sjq.wanandroid02.adapters.OnBlogItemClickListener;
import com.android.sjq.wanandroid02.adapters.OnClassifyItemClickListener;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.modles.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid02.views.view.NewTechnologyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */

public class NewTechnologyPresenter extends BasePresenter<NewTechnologyView> implements OnClassifyItemClickListener, OnBlogItemClickListener {
    private ArrayList<ClassifyEntity> mClassifies;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlies;

    public void initCategoryData() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                try {
                    Document doc = Jsoup.connect(Contacts.NEW_TECHNOLOGY_URL).get();
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
                    adapter.setOnItemClickListener(NewTechnologyPresenter.this);
                    view.setCategoryAdapter(adapter);
                    getBlogData(mClassifies.get(0));
                } else {
                    view.showShorToast("数据请求失败！！！");
                    return;
                }
            }
        }.execute();
    }


    public void getBlogData(final ClassifyEntity entity) {
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
                    adapter.setOnItemClickListener(NewTechnologyPresenter.this);
                    view.setBlogAdapter(adapter);
                } else {
                    view.showShorToast("数据请求失败！！！");
                    return;
                }
            }
        }.execute();
    }


    @Override
    public void onItemClick(ClassifyEntity entity, RecyclerView.ViewHolder viewHolder) {
        view.onCategoryClick(entity);
    }

    @Override
    public void onBlogNameClickListener(int position) {
        view.onBlogClick(mRecentlies.get(position));
    }

    @Override
    public void onClassifyNameClickListener(int position) {

    }
}
