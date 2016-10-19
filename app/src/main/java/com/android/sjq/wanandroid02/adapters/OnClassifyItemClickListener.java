package com.android.sjq.wanandroid02.adapters;

import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid02.modles.ClassifyEntity;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface OnClassifyItemClickListener<VH extends RecyclerView.ViewHolder> {
    void onItemClick(ClassifyEntity entity, VH viewHolder);
}
