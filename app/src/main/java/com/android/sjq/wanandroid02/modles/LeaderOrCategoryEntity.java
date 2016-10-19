package com.android.sjq.wanandroid02.modles;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class LeaderOrCategoryEntity {
    private String LeaderName;
    private ArrayList<SubClassEntity> subClassEntities;
    private String categoryUrl;

    public String getLeaderName() {
        return LeaderName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public ArrayList<SubClassEntity> getSubClassEntities() {
        return subClassEntities;
    }

    public void setSubClassEntities(ArrayList<SubClassEntity> subClassEntities) {
        this.subClassEntities = subClassEntities;
    }

    @Override
    public String toString() {
        return "LeaderEntity{" +
                "LeaderName='" + LeaderName + '\'' +
                ", subClassEntities=" + subClassEntities +
                '}';
    }
}
