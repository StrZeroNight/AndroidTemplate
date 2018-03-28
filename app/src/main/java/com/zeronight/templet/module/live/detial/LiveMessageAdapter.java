package com.zeronight.templet.module.live.detial;

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
 * Created by Administrator on 2018/2/7.
 */

public class LiveMessageAdapter extends BaseAdapter<LiveMessageBean> {

    private OnButtonClickListenr onButtonClickListenr;

    public LiveMessageAdapter(Context mContext, List<LiveMessageBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;
        LiveMessageBean liveMessageBean = mList.get(position);
        String avatar = liveMessageBean.getAvatar();
        String msg = liveMessageBean.getMsg();
        String user_name = liveMessageBean.getUser_name();

        mHolder.tv_message.setText(msg);
        if (user_name.equals("")) {
            mHolder.tv_name.setText("用户");
        }else{
            mHolder.tv_name.setText(user_name);
        }
        ImageLoad.loadCircleImage(avatar , mHolder.iv_header);

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_livemessage, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private ImageView iv_header;
        private TextView tv_name;
        private TextView tv_message;
        public BaseViewHolder(View itemView) {
            super(itemView);
            iv_header = (ImageView) itemView.findViewById(R.id.iv_header);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

}
