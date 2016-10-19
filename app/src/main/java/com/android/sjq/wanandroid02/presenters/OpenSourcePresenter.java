package com.android.sjq.wanandroid02.presenters;

import android.os.AsyncTask;

import com.android.sjq.wanandroid02.Contacts;
import com.android.sjq.wanandroid02.base.BasePresenter;
import com.android.sjq.wanandroid02.modles.LeaderOrCategoryEntity;
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

public class OpenSourcePresenter extends BasePresenter<OpenSourceView> {
    private ArrayList<LeaderOrCategoryEntity> leaderEntitiesl;

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
                                subClassEntity.setSubClassUrl(ee.select("a").attr("href"));
                                // System.out.println("   " + ee.select("a").text());
                                subClassEntities.add(subClassEntity);
                            }
                            entity.setSubClassEntities(subClassEntities);
                            i += (es1.size() - 1);
                        } else {
                            entity.setLeaderName(e.select("a").text());
                            entity.setCategoryUrl(e.select("a").attr("href"));
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
            }
        }.execute();
    }
}
