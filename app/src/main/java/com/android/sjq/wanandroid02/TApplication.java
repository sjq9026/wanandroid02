package com.android.sjq.wanandroid02;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/10/19.
 */

public class TApplication extends Application {
   public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
