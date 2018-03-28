package com.zeronight.templet.module.live.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.utils.ImageLoad;
import com.zeronight.templet.common.utils.TimeUtils;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class LiveAdapter extends BaseAdapter<LiveListBean> {

    private OnButtonClickListenr onButtonClickListenr;

    public LiveAdapter(Context mContext, List<LiveListBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        LiveListBean liveListBean = mList.get(position);
        String title = liveListBean.getTitle();
        String host_name = liveListBean.getHost_name();
        String start_time = liveListBean.getStart_time();
        String img = liveListBean.getImg();
        String status = liveListBean.getStatus();

        mHolder.tv_master.setText(host_name);
        mHolder.tv_time.setText(start_time);
        mHolder.tv_title.setText(title);
        ImageLoad.loadImage(img , mHolder.iv_pic);
        //1未开始 2直播中 3已结束
        if (status.equals("1")) {
            String startTime = TimeUtils.converYMDHMSToHM(start_time);
            mHolder.tv_tag.setText(startTime);
            mHolder.tv_tag.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else if (status.equals("2")) {
            mHolder.tv_tag.setText("直播中");
            mHolder.tv_tag.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            mHolder.tv_tag.setText("已结束");
            mHolder.tv_tag.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_live, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private ImageView iv_pic;
        private TextView tv_tag;
        private TextView tv_master;
        private TextView tv_title;
        private TextView tv_time;
        public BaseViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);
            tv_master = (TextView) itemView.findViewById(R.id.tv_master);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
