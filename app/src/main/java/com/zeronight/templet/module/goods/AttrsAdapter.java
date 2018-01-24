package com.zeronight.templet.module.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;

import java.text.ParseException;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/1/22.
 */
public class AttrsAdapter extends BaseAdapter<AttrsBean> {

    public static TreeMap<AttrsBean, AttrsBean2> attrsMap = new TreeMap<>();//用来存放全部属性，获取属性也从这里获取

    public AttrsAdapter(Context mContext, List<AttrsBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        final BaseViewHolder mHolder = (BaseViewHolder) holder;
        AttrsBean attrsBean = mList.get(position);
        String title = attrsBean.getTitle();
        final List<AttrsBean2> attrs = attrsBean.getAttrs();
        mHolder.tv_title.setText(title);
        //
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        mHolder.rv_attrs.setLayoutManager(layoutManager);
        final AttrsAdapter2 attrsAdapter2 = new AttrsAdapter2(mContext, attrs , attrsBean);
        mHolder.rv_attrs.setAdapter(attrsAdapter2);
        attrsAdapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void click(int position) {
                attrsAdapter2.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_goodattrs , parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_title;
        private RecyclerView rv_attrs;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            rv_attrs = (RecyclerView) itemView.findViewById(R.id.rv_attrs);
        }
    }

}
