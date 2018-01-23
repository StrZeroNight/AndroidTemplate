package com.zeronight.templet.module.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.dalimao.library.DragView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;

/**
 * Created by Administrator on 2018/1/18.
 */
public class SimpleView extends DragView {

    TextView tv_show;
    int i = 0;

    public SimpleView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_window, this);
//        tv_show = view.findViewById(R.id.tv_show);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    login();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    private void login() {
        i++;
//        tv_show.setText(i);
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl("http://baimu.tjtomato.com/Client/auth/login")
                .setParams("phone", "15222898621")
                .setParams("password", "111111")
                .setParams("registration_id", "")
                .setParams("equipment_id", "")
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