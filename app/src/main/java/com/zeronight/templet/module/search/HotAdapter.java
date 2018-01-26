package com.zeronight.templet.module.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.widget.SuperTextView;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class HotAdapter extends BaseAdapter<String> {

    private OnButtonClickListenr onButtonClickListenr;

    public HotAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        String s = mList.get(position);
        mHolder.stv_hot.setText(s);
        mHolder.stv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onButtonClickListenr != null) {
                    onButtonClickListenr.OnButtonClick(position);
                }
            }
        });
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_hot, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private SuperTextView stv_hot;
        public BaseViewHolder(View itemView) {
            super(itemView);
            stv_hot = itemView.findViewById(R.id.stv_hot);
        }
    }

    public interface OnButtonClickListenr{
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr){
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
