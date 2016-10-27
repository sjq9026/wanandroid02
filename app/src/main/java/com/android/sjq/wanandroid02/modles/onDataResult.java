package com.android.sjq.wanandroid02.modles;

/**
 * Created by Administrator on 2016/10/21.
 */

public interface onDataResult<T> {
    void onSuccess(T data);

    void onFailure(String msg);

}
