package com.zeronight.templet.module.bankcard.bank;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.utils.ImageLoad;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */

public class BankAdapter extends BaseAdapter<BankBean> {

    public BankAdapter(Context mContext, List<BankBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;

        BankBean bankCardBean = mList.get(position);
        String bankName = bankCardBean.getTitle();
        String image = bankCardBean.getImage();

        mHolder.tv_bank_name.setText(bankName);
        ImageLoad.loadCircleImage(image , mHolder.iv_bank_icon);
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        //在这里监听点击事件
        View view = mLayoutInflater.inflate(R.layout.item_bank, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }


    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private ImageView iv_bank_icon;
        private TextView tv_bank_name;
        public BaseViewHolder(View itemView) {
            super(itemView);
            iv_bank_icon = (ImageView) itemView.findViewById(R.id.iv_bank_icon);
            tv_bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
        }
    }



}
