package com.zeronight.templet.zfbapi;


import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.module.main.BasePresenter;

/**
 * Created by Administrator on 2017/3/9.
 */

public class PayPresenter extends BasePresenter {

    /**
     * 生成微信订单 获取参数 获取成功直接调起微信支付
     */
    public void confirmPayByWx(String oid){
//        token	是	string
//        oid	是	int	订单id
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("token", "")
                .setParams("oid", oid)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onNoData() {

            }

            @Override
            public void onServerError() {

            }
        });
    }

}
