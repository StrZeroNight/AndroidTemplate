package com.zeronight.templet.module.live.detial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.CommenMethod;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ImageLoad;
import com.zeronight.templet.common.utils.RecyclerviewUtils;
import com.zeronight.templet.common.utils.TimeUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class LiveDetialActivity extends BaseActivity implements View.OnClickListener {

    private final static String ID = "ID";
    private LinearLayout ll_x;
    private TitleBar titlebar;
    private ImageView iv_image;
    private TextView tv_tag;
    private ImageView iv_banner;
    private TextView tv_master;
    private TextView tv_titlez;
    private TabLayout tablayout;
    private XRecyclerView xrv_message;
    private RecyclerView rv_guess;
    private EditText et_message;
    private SuperTextView stv_send;
    private GSYBaseVideoPlayer gsyvideoplayer;
    //
    private String id;
    private List<LiveMessageBean> messageList = new ArrayList<>();
    private List<String> guessList = new ArrayList<>();
    private LiveMessageAdapter liveMessageAdapter;
    private boolean isFull = false;
    private boolean isGetMessage = true;
    private LiveGuessAdapter liveGuessAdapter;


    public static void start(Context context, String id) {
        Intent it = new Intent(context, LiveDetialActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            id = intent.getStringExtra(ID);
            getLiveDetial(id);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedetail);
        initView();
        initIntent();
        getMessage("");
    }

    private void initView() {
        ll_x = findViewById(R.id.ll_x);
        gsyvideoplayer = findViewById(R.id.gsyvideoplayer);
        titlebar =  findViewById(R.id.titlebar);
        iv_image =  findViewById(R.id.iv_image);
        tv_tag = findViewById(R.id.tv_tag);
        iv_banner =  findViewById(R.id.iv_banner);
        tv_master =  findViewById(R.id.tv_master);
        tv_titlez =  findViewById(R.id.tv_titlez);
        tablayout =  findViewById(R.id.tablayout);
        et_message =  findViewById(R.id.et_message);
        stv_send = findViewById(R.id.stv_send);
        stv_send.setOnClickListener(this);
        iniTablayout();
        initMessageRv();
        initGuessRv();
        startPlayer();
    }

    private void startPlayer() {
        // 视频返回
        gsyvideoplayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandardGSYVideoPlayer.releaseAllVideos();
                isFull = false;
            }
        });

        // 开启全屏
        gsyvideoplayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gsyvideoplayer.startWindowFullscreen(LiveDetialActivity.this, false, true);
                isFull = true;
            }
        });

    }

    private void initMessageRv() {
        xrv_message = (XRecyclerView) findViewById(R.id.xrv_message);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        liveMessageAdapter = new LiveMessageAdapter(this, messageList);
        xrv_message.setAdapter(liveMessageAdapter);
        new RecyclerviewUtils().setNestScrollRecyclerview(linearLayoutManager1 , xrv_message);
        xrv_message.setPullRefreshEnabled(false);
    }

    private void initGuessRv() {
        rv_guess = (RecyclerView) findViewById(R.id.rv_guess);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_guess.setLayoutManager(linearLayoutManager);
        liveGuessAdapter = new LiveGuessAdapter(this, guessList);
        liveGuessAdapter.setOnButtonClickListenr(new LiveGuessAdapter.OnButtonClickListenr() {
            @Override
            public void OnButtonClick(int position) {
                et_message.setText(guessList.get(position).toString());
            }
        });
        rv_guess.setAdapter(liveGuessAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_send:
                commenMethod.isLogined(new CommenMethod.LoginClickListener() {
                    @Override
                    public void logined() {
                        String message = et_message.getText().toString();
                        send(message);
                    }
                });
                break;
        }
    }

    public void iniTablayout() {
        List<String> tabNames = new ArrayList<>();
        tabNames.add("大家说");
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (int i = 0; i < tabNames.size(); i++) {
                tablayout.addTab(tablayout.newTab().setText(tabNames.get(i).toString()));
            }
            //监听viewpager滑动
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

        }


    private void getLiveDetial(String id) {
        showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("id", id)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
                LiveDetailBean liveDetailBean = JSON.parseObject(data, LiveDetailBean.class);
                String title = liveDetailBean.getTitle();
                String host_name = liveDetailBean.getHost_name();
                String start_time = liveDetailBean.getStart_time();
                String img = liveDetailBean.getImg();
                String live_url = liveDetailBean.getLive_url();
                String status = liveDetailBean.getStatus();

                List<String> guess_you_say = liveDetailBean.getGuess_you_say();
                guessList.clear();
                if (guess_you_say != null && guess_you_say.size() > 0) {
                    guessList.addAll(guess_you_say);
                    liveGuessAdapter.notifyDataSetChanged();
                }

                tv_titlez.setText(title);
                tv_master.setText(host_name);
                ImageLoad.loadImage(img , iv_image);
                if (status.equals("1")) {
                    String startTime = TimeUtils.converYMDHMSToHM(start_time);
                    tv_tag.setText(startTime);
                    tv_tag.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    iv_image.setVisibility(View.VISIBLE);
                    gsyvideoplayer.setVisibility(View.GONE);
                    tv_tag.setVisibility(View.VISIBLE);
                    ll_x.setVisibility(View.GONE);
                }else if (status.equals("2")) {
                    tv_tag.setText("直播中");
                    tv_tag.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    iv_image.setVisibility(View.GONE);
                    ll_x.setVisibility(View.VISIBLE);
                    gsyvideoplayer.setVisibility(View.VISIBLE);
                    //开启轮询获取消息
                    handler.postDelayed(runnable, TIME);
                    tv_tag.setVisibility(View.GONE);
                    startPlayer(live_url);
                }else {
                    tv_tag.setText("已结束");
                    tv_tag.setVisibility(View.VISIBLE);
                    tv_tag.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    iv_image.setVisibility(View.VISIBLE);
                    gsyvideoplayer.setVisibility(View.GONE);
                    ll_x.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNoData() {
                dismissProgressDialog();
            }

            @Override
            public void onServerError() {
                dismissProgressDialog();
            }
        });
    }

    private void startPlayer(String live_url) {
        //测试路径
//        gsyvideoplayer.setUp("http://jinchen.tjtomato.com/data/upload/user/20180314/5aa8889478364.mp4" , false, "");
        gsyvideoplayer.setUp(CommonUrl.IMAGE_URL + live_url, false, "");
        gsyvideoplayer.setRotateViewAuto(true);
        gsyvideoplayer.setRotateWithSystem(true);
        gsyvideoplayer.setShowFullAnimation(true);
        gsyvideoplayer.startPlayLogic();
    }

    private void send(String message) {
        if (id.equals("")) {
            ToastUtils.showMessage("直播间出错请返回重试");
            return ;
        }
        showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("message", message)
                .setParams("video_id", id)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                SendBean sendBean = JSON.parseObject(data, SendBean.class);
                dismissProgressDialog();
                ToastUtils.showMessage("发送成功");
                String video_msg_id = sendBean.getVideo_msg_id();
                if (XStringUtils.isStringAreNum(video_msg_id)) {
                    int previousId = Integer.parseInt(video_msg_id) - 1;
                    getMessage(previousId + "");
                }else{
                    ToastUtils.showMessage("消息发送错误");
                }
            }

            @Override
            public void onNoData() {
                dismissProgressDialog();
            }

            @Override
            public void onServerError() {
                dismissProgressDialog();
            }
        });
    }

    private void getMessage(String video_msg_id) {
//        video_id	否	int	直播id
//        video_msg_id	否	int	最后一条消息的id （第一请求接口可不传）
        if (id.equals("")) {
            ToastUtils.showMessage("直播间出错请返回重试");
            return ;
        }
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("video_id", id)
                .setParams("video_msg_id", video_msg_id)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
            }

            @Override
            public void onSuccess(String data) {
                List<LiveMessageBean> liveMessageBeans = JSON.parseArray(data, LiveMessageBean.class);
                messageList.addAll(liveMessageBeans);
                liveMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNoData() {
            }

            @Override
            public void onServerError() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gsyvideoplayer.release();
        handler.removeCallbacks(runnable);
    }

    //机械返回键控制
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isFull) {
                StandardGSYVideoPlayer.backFromWindowFull(LiveDetialActivity.this);
                isFull = false;
            }else{
                finish();
            }
        }
        return false;
    }

    private int TIME = 2000;  //每隔1s执行一次.
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (isGetMessage) {
                    handler.postDelayed(this, TIME);
                    getMessage(messageList.get(messageList.size() - 1).getVideo_msg_id());
                }else{

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
