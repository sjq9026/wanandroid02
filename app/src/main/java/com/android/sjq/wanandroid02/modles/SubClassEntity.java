package com.android.sjq.wanandroid02.modles;

/**
 * Created by Administrator on 2016/10/17.
 */

public class SubClassEntity {

    private String SubClassName;
    private String SubClassUrl;

    public String getSubClassName() {
        return SubClassName;
    }

    public void setSubClassName(String subClassName) {
        SubClassName = subClassName;
    }

    public String getSubClassUrl() {
        return SubClassUrl;
    }

    public void setSubClassUrl(String subClassUrl) {
        SubClassUrl = subClassUrl;
    }

    @Override
    public String toString() {
        return "SubClassEntity{" +
                "SubClassName='" + SubClassName + '\'' +
                ", SubClassUrl='" + SubClassUrl + '\'' +
                '}';
    }
}
