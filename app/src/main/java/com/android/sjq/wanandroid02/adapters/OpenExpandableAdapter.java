package com.android.sjq.wanandroid02.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.android.sjq.wanandroid02.R;
import com.android.sjq.wanandroid02.modles.LeaderOrCategoryEntity;
import com.android.sjq.wanandroid02.tool.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/18.
 */

public class OpenExpandableAdapter implements ExpandableListAdapter {
    private ArrayList<LeaderOrCategoryEntity> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private OnGroupItemClickListener listener;


    public OpenExpandableAdapter(ArrayList<LeaderOrCategoryEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        if (mList.get(groupPosition).getSubClassEntities() != null && mList.get(groupPosition).getSubClassEntities().size() > 0) {
            childCount = mList.get(groupPosition).getSubClassEntities().size();
        }
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getSubClassEntities().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.knowledge_lv_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.name_tv);
        tv.setText(mList.get(groupPosition).getLeaderName());
        tv.setHeight(80);
        tv.setTextColor(Color.parseColor("#542642"));
        tv.setPadding(10, 0, 0, 0);
        if (mList.get(groupPosition).getSubClassEntities() == null || mList.get(groupPosition).getSubClassEntities().size() == 0) {
           Log.print("onGroupItemClick",groupPosition+"---->"+"添加Click");
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onGroupItemClickListener(groupPosition, -1);
                    }
                }
            });
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.knowledge_lv_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.name_tv);
        tv.setText(mList.get(groupPosition).getSubClassEntities().get(childPosition).getSubClassName());
        tv.setHeight(80);
        tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        tv.setPadding(20, 0, 0, 0);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    public void setOnGroupItemClickListener(OnGroupItemClickListener listener) {
        this.listener = listener;
    }
}
