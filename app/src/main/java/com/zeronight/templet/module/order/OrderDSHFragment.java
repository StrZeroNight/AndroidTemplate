package com.zeronight.templet.module.order;

/**
 * Created by Administrator on 2018/5/14.
 */

public class OrderDSHFragment extends OrderAllFragment {

    @Override
    protected void initList() {
        tv_tip_order.setText("待收货");
//        ActivityListUpBean activityListUpBean = new ActivityListUpBean();
//        activityListUpBean.setType(type); //1.进行中的 2.已结束的 默认1
        getList();
    }

}
