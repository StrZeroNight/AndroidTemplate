package com.zeronight.templet.module.goods;

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
 * Created by Administrator on 2018/1/23.
 */

public class AttrsAdapter extends BaseAdapter<String> {

    public AttrsAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        String s = mList.get(position);
        mHolder.tv_attrs.setText(s);
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_attrflow, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder implements View.OnClickListener {
        private TextView tv_attrs;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_attrs = (TextView) itemView.findViewById(R.id.tv_attrs);
            tv_attrs.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_attrs:
                    
                    break;
            }
        }
    }


}
