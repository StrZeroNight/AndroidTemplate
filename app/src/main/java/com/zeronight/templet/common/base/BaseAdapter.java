package com.zeronight.templet.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.List;

/**
 * adapter的基类
 * <p>
 * Created by Administrator on 2016/6/25.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    protected Context mContext;
    protected List<T> mList;
    protected LayoutInflater mLayoutInflater;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseAdapter(Context mContext, List<T> mList) {
        this.mContext = mContext;
        if (mList != null) {
            this.mList = mList;
        }
        if (mContext != null) {
            mLayoutInflater = LayoutInflater.from(mContext);
        }
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return createRecyclerViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.itemView.setTag(position);
        try {
            showViewHolder(holder, position);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mList.size() > 0) {
            count = mList.size();
        }
        return count;
    }

    public interface OnItemClickListener {
        void click(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemLongClickListener {
        void longClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    protected abstract void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException;

    protected abstract BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.click((int)v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(mOnItemLongClickListener != null) {
            mOnItemLongClickListener.longClick((int)v.getTag());
        }
        return true;
    }


}
