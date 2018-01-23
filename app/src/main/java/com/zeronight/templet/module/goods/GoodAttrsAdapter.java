package com.zeronight.templet.module.goods;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */
public class GoodAttrsAdapter extends BaseAdapter<GoodAttrsBean> {

    public GoodAttrsAdapter(Context mContext, List<GoodAttrsBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        final BaseViewHolder mHolder = (BaseViewHolder) holder;
        GoodAttrsBean goodAttrsBean = mList.get(position);
        String title = goodAttrsBean.getTitle();
        final List<String> attrs = goodAttrsBean.getAttrs();
        mHolder.tv_title.setText(title);
//        mHolder.tagflow.setAdapter(new TagAdapter<String>(attrs) {
//            @Override
//            public View getView(FlowLayout parent, int position, String content) {
//                View view = LayoutInflater.from(mContext).inflate(R.layout.item_attrflow , null);
//                TextView tv = (TextView)view.findViewById(R.id.tv_attrs);
//                tv.setText(content);
//                return view;
//            }
//        });
//        mHolder.tagflow.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> selectPosSet) {
//
//            }
//        });
//        mHolder.tagflow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                ToastUtils.showMessage("attrs:" + attrs.get(position));
//                return true;
//            }
//        });
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
        private TagFlowLayout tagflow;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
//            tagflow = (TagFlowLayout) itemView.findViewById(R.id.tagflow);
        }
    }

}
