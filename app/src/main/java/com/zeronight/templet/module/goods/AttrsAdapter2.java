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

import static com.zeronight.templet.module.goods.AttrsAdapter.attrsMap;

/**
 * Created by Administrator on 2018/1/23.
 */

public class AttrsAdapter2 extends BaseAdapter<AttrsBean2> {

    private AttrsBean attrsBean; //父attrs的id 用来存放map

    public AttrsAdapter2(Context mContext, List<AttrsBean2> mList , AttrsBean attrsBean) {
        super(mContext, mList);
        this.attrsBean = attrsBean;
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        final BaseViewHolder mHolder = (BaseViewHolder) holder;
        final AttrsBean2 attrsBean2 = mList.get(position);
        String content = attrsBean2.getContent();
        String attrId = attrsBean2.getId();
        mHolder.tv_attrs.setText(content);

        mHolder.tv_attrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attrsMap.put(attrsBean, attrsBean2);
                notifyDataSetChanged();
            }
        });

        if (attrsMap.get(attrsBean) != null) {
            String parentAttrId = attrsMap.get(attrsBean).getId();
            if (parentAttrId.equals(attrId)) {
                mHolder.tv_attrs.setSelected(true);
            }else{
                mHolder.tv_attrs.setSelected(false);
            }
        }

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_attrflow, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_attrs;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_attrs = (TextView) itemView.findViewById(R.id.tv_attrs);
        }
    }


}
