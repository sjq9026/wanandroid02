package com.android.sjq.wanandroid02.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.modles.ClassifyEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */

public class FirstCategoryAdapter extends RecyclerView.Adapter<ClassifyViewHolder> {
    private Context mContext;
    private ArrayList<ClassifyEntity> mList;
    private LayoutInflater inflater;
    private OnClassifyItemClickListener listener;

    public FirstCategoryAdapter(Context context, ArrayList<ClassifyEntity> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassifyViewHolder(mContext, inflater.inflate(R.layout.category_item_layout, null));
    }

    @Override
    public void onBindViewHolder(final ClassifyViewHolder holder, final int position) {
        holder.getClassifyName().setText(mList.get(position).getClassifyName());
        holder.getClassifyName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(mList.get(position),holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnClassifyItemClickListener listener){
        this.listener = listener;
    }
}
