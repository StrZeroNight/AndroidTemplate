package com.zeronight.templet.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.utils.ListManager;

import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/5/14.
 */

public class OrderAllFragment extends BaseFragment {

    private ListManager<String> listManager;
    private XRecyclerView xrv_all;
    protected TextView tv_tip_order; //只是为了测试用

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderall, null);
        xrv_all = view.findViewById(R.id.xrv_all);
        tv_tip_order = view.findViewById(R.id.tv_tip_order);
//        EventBus.getDefault().register(this);
        initList();
        return view;
    }

    protected void initList(){
//        ActivityListUpBean activityListUpBean = new ActivityListUpBean();
//        activityListUpBean.setType(type); //1.进行中的 2.已结束的 默认1
        getList();
    }

    protected void getList(){
        listManager = new ListManager<>((AppCompatActivity) getActivity());
        listManager.setRecyclerView(xrv_all)
                .setLayoutManagerType(ListManager.LINEAR_LAYOUT)
//                .setParamsObject(activityListUpBean)
                .setParamsObject(new Object())
                .setAdapter(new OrderAdapter(getActivity(), listManager.getAllList()))
                .setUrl(CommonUrl.login)
                .isLoadMore(true)
                .isPullRefresh(true)
//                .setOnReceiveHttpData(new ListManager.OnReceiveHttpDataListener() {
//                    @Override
//                    public JSONArray ReceiveHttpData(String data) {
//                        //处理不是纯列表的数据
//                        List<ActivityListBean> activityListBeans = JSONObject.parseArray(data, ActivityListBean.class);
//                        return ListManager.getJSONArrayFromList(activityListBeans);
//                    }
//                })
//                .setOnItemClickListener(new ListManager.OnItemClickListener() {
//                    @Override
//                    public void OnItemClick(int position) {
//                        ActivityDetialActivity.start(getActivity() , listManager.getAllList().get(position).getAid());
//                    }
//
//                    @Override
//                    public void OnItemLongClick(int position) {
//
//                    }
//                })
                .run();
    }

    public final static String NOTIFY_ACTIVITY_ADD2 = "NOTIFY_ACTIVITY_ADD2";

    @Subscribe
    public void notifyActivityAdd2(EventBusBundle eventBusBundle) {
        if (eventBusBundle.getKey().equals(NOTIFY_ACTIVITY_ADD2)) {
            listManager.refresh();
        }
    }

    @Subscribe
    public void notifyActivity(EventBusBundle eventBusBundle) {
//        if (eventBusBundle.getKey().equals(ActivityListActivity.NOTIFY_ACTIVITY)) {
//            listManager.refresh();
//        }
    }

//    @Override
//    public void onDestroy() {
//        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }

}
