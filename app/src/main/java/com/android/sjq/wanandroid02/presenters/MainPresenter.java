package com.android.sjq.wanandroid02.presenters;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;
import com.android.sjq.wanandroid02.tool.Log;
import com.android.sjq.wanandroid02.views.MainView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MainPresenter extends BasePresenter<MainView> {
    //获取大分类数据
    public void getClassify() {
        new AsyncTask<String, Integer, ArrayList<ClassifyEntity>>() {
            ArrayList<ClassifyEntity> mClassifies = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected ArrayList<ClassifyEntity> doInBackground(String... params) {
                Document document;
                mClassifies = new ArrayList<>();
                try {
                    document = Jsoup.connect(Contacts.BASE_URL).get();
                    Elements classifiesEle = document.getElementsByClass("menu").select("li");
                    ClassifyEntity classifyEntity;
                    for (Element element : classifiesEle) {
                        classifyEntity = new ClassifyEntity();
                        classifyEntity.setClassifyName(element.select("li").text());
                        classifyEntity.setHttp_url(Contacts.BASE_URL + element.select("li").select("a").attr("href"));
                        mClassifies.add(classifyEntity);
                    }
                    Log.print("TAG", mClassifies.toString());
                } catch (IOException e) {
                    document = null;
                    e.printStackTrace();
                }
                if (mClassifies != null && mClassifies.size() > 0) {
                    return mClassifies;
                } else {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(ArrayList<ClassifyEntity> list) {
                super.onPostExecute(list);
                //设置界面显示
                view.setClassify(list);
            }
        }.execute();
    }
}
