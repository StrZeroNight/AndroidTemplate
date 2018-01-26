package com.zeronight.templet.module.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class SearchAdapter extends BaseAdapter<String> {


    public SearchAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        String s = mList.get(position);
        mHolder.tv_search.setText(s);

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_search, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_search;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_search = itemView.findViewById(R.id.tv_search);
        }
    }

}
