package com.zeronight.templet.module.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.widget.OrderButton;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class OrderAdapter extends BaseAdapter<String> {

    private OnButtonClickListenr onButtonClickListenr;

    public OrderAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        mHolder.ob.setOrderType(position);
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_order, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_ordernum;
        private TextView tv_status;
        private RecyclerView rv_pro;
        private TextView tv_num;
        private TextView tv_money;
        private OrderButton ob;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_ordernum = (TextView) itemView.findViewById(R.id.tv_ordernum);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            rv_pro = (RecyclerView) itemView.findViewById(R.id.rv_pro);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            ob = (OrderButton) itemView.findViewById(R.id.ob);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
