package com.zeronight.templet.module.classify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */

public class BrandAdapter extends BaseAdapter<String> {

    // TODO: 2018/2/6 应该放实体类
    public static Map<Integer , BrandBean> brandMap = new HashMap();

    private OnButtonClickListenr onButtonClickListenr;

    public BrandAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        final String s = mList.get(position).toString();
        if (brandMap.containsKey(position)) {
            mHolder.iv_choose.setImageResource(R.drawable.select_on);
        }else{
            mHolder.iv_choose.setImageResource(R.drawable.select);
        }

        mHolder.rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brandMap.containsKey(position)) {
                    brandMap.remove(position);
                }else{
                    brandMap.put(position , new BrandBean((position + 1) + "" , s));
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_brand, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private RelativeLayout rl_root;
        private ImageView iv_choose;
        public BaseViewHolder(View itemView) {
            super(itemView);
            iv_choose = itemView.findViewById(R.id.iv_choose);
            rl_root = itemView.findViewById(R.id.rl_root);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
