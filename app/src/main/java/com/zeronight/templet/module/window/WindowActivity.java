package com.zeronight.templet.module.window;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;
import com.zeronight.templet.R;
import com.zeronight.templet.common.application.BaseApplication;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ToastUtils;

/**
 * 这个类和商城无关 是一个小窗口测试类
 * Created by Administrator on 2018/1/18.
 */
public class WindowActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private Button btn_show;
    private TextView tv_close;
    private int i = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };

    public static void start(Context context, String id) {
        Intent it = new Intent(context, WindowActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, WindowActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, WindowActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            String id = intent.getStringExtra(ID);
            ToastUtils.showMessage("获取id" + id);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        initView();
//        addSimpleView();

    }

//    public void addSimpleView() {
//        SimpleView floatView = new SimpleView(this);
//        FloatUtil.showFloatView(floatView, null);
//        floatView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FloatUtil.hideFloatView(WindowActivity.this, SimpleView.class, false);
//            }
//        });
//    }

    private void initView() {
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                Intent it = new Intent(WindowActivity.this, WindowService.class);
                startService(it);
                View view = LayoutInflater.from(BaseApplication.getInstance())
                        .inflate(R.layout.view_window, null, false);
                tv_close = view.findViewById(R.id.tv_close);
                FloatWindow
                        .with(getApplicationContext())
                        .setView(view)
                        .setWidth(Screen.width, 0.2f)
                        .setHeight(Screen.width, 0.2f)
                        .setX(Screen.width, 0.7f)
                        .setY(Screen.height, 0.2f)
                        .setMoveType(MoveType.back)
                        .setMoveStyle(300, null)
                        .setDesktopShow(true)
                        .build();
                FloatWindow.get().show();
                UpdateUI updateUI = new UpdateUI();
                Thread td1 = new Thread(updateUI, "td1");
                td1.start();
                break;
        }
    }

    class UpdateUI implements Runnable {
        @Override
        public void run() {
            // 如果没有中断就一直运行
            while (!Thread.currentThread().isInterrupted()) {
                getUserInfo();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getUserInfo() {
        //获取用户信息
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl("http://www.bjjxhm.cn/client/member/index")
                .setParams("token", "671a3d2c4578f51d84423011ac078053")
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
                tv_close.setText(i + "-----" + data);
                i++;
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
