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
 * Created by Administrator on 2018/2/1.
 */

public class ParamsAdapter extends BaseAdapter<String> {


        private OnButtonClickListenr onButtonClickListenr;

        public ParamsAdapter(Context mContext, List<String> mList) {
            super(mContext, mList);
        }

        @Override
        protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
            BaseViewHolder mHolder = (BaseViewHolder) holder;


        }

        @Override
        protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.item_params, parent, false);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            return new BaseViewHolder(view);
        }

        public class BaseViewHolder extends BaseRecyclerViewHolder {
            private TextView tv_paramsname;
            private TextView tv_params;
            public BaseViewHolder(View itemView) {
                super(itemView);
                tv_paramsname = (TextView) itemView.findViewById(R.id.tv_paramsname);
                tv_params = (TextView) itemView.findViewById(R.id.tv_params);
            }
        }

        public interface OnButtonClickListenr{
            void OnButtonClick(int position);
        }

        public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr){
            this.onButtonClickListenr = onButtonClickListenr;
        }

}
