package com.zeronight.templet.module.live.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.utils.ListManager;
import com.zeronight.templet.module.live.detial.LiveDetialActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class LiveFragment extends BaseFragment {

    private XRecyclerView xrv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        initView(view);
        initLive();
        return view;
    }

    private void initView(View view) {
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
    }

    private void initLive() {
        final ListManager<LiveListBean> listManager = new ListManager<>((AppCompatActivity) getActivity());
        listManager.setRecyclerView(xrv)
                .setLayoutManagerType(ListManager.LINEAR_LAYOUT)
                .setParamsObject(new Object())
                .setAdapter(new LiveAdapter(getActivity(), listManager.getAllList()))
                .setUrl(CommonUrl.login)
                .isLoadMore(true)
                .isPullRefresh(true)
                .setOnReceiveHttpData(new ListManager.OnReceiveHttpDataListener() {
                    @Override
                    public JSONArray ReceiveHttpData(String data) {
                        //处理不是纯列表的数据
                        List<LiveListBean> liveListBeans = JSONObject.parseArray(data, LiveListBean.class);
                        return ListManager.getJSONArrayFromList(liveListBeans);
                    }
                })
                .setOnItemClickListener(new ListManager.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        LiveDetialActivity.start(getActivity(), listManager.getAllList().get(position).getId());
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }
                })
                .run();
    }

}
