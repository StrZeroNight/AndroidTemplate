package com.zeronight.templet.module.live.detial;

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
 * Created by Administrator on 2018/2/7.
 */

public class LiveGuessAdapter extends BaseAdapter<String> {


    private OnButtonClickListenr onButtonClickListenr;

    public LiveGuessAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        mHolder.stv_content.setText(mList.get(position).toString());
        mHolder.stv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListenr.OnButtonClick(position);
            }
        });
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_liveguess, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private SuperTextView stv_content;

        public BaseViewHolder(View itemView) {
            super(itemView);
            stv_content = itemView.findViewById(R.id.stv_content);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
