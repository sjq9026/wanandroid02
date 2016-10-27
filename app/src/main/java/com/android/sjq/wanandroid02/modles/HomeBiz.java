package com.android.sjq.wanandroid02.modles;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.TApplication;
import com.android.sjq.wanandroid02.adapters.RecentlyBlogAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */

public class HomeBiz {
    private ArrayList<RecentlyBlogInfoEntity> mRecentlyBlogs;

    public void getRecentlyData(final onDataResult dataResult) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                Document document;
                mRecentlyBlogs = new ArrayList<>();
                try {
                    document = Jsoup.connect(Contacts.BASE_URL).get();
                    RecentlyBlogInfoEntity recentlyBlogInfoEntity = null;
                    Elements elements = document.getElementsByClass("item_div");
                    for (Element e : elements) {
                        Elements e1 = e.getElementsByClass("article_div");
                        recentlyBlogInfoEntity = new RecentlyBlogInfoEntity();
                        recentlyBlogInfoEntity.setBlogname(e1.select("a").get(0).text().toString());
                        recentlyBlogInfoEntity.setBlogaddress(e1.select("a").get(0).attr("href").toString());
                        recentlyBlogInfoEntity.setAuthor(e1.select("span").get(0).text().toString());
                        recentlyBlogInfoEntity.setSource(e1.select("span").get(1).text().toString());
                        recentlyBlogInfoEntity.setClassify(e1.select("span").get(2).text().toString());
                        recentlyBlogInfoEntity.setClassifyaddress(Contacts.BASE_URL + e1.select("span").get(2).select("a").attr("href").toString());
                        recentlyBlogInfoEntity.setDate(e1.select("span").get(3).text().toString());
                        mRecentlyBlogs.add(recentlyBlogInfoEntity);
                    }
                } catch (IOException e) {
                    document = null;
                    e.printStackTrace();
                }
                if (mRecentlyBlogs != null && mRecentlyBlogs.size() > 0) {
                    return "OK";
                } else {
                    return "ERR";
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                RecentlyBlogAdapter adapter =
                        new RecentlyBlogAdapter(TApplication.mContext, mRecentlyBlogs);
                dataResult.onSuccess(adapter);
            }
        }.execute();
    }


    public ArrayList<RecentlyBlogInfoEntity> getDataList() {
        if (mRecentlyBlogs == null) {
            return new ArrayList<>();
        }
        return mRecentlyBlogs;
    }
}
