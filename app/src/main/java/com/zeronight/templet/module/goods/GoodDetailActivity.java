package com.zeronight.templet.module.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class GoodDetailActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private TitleBar titlebar;
    private SuperTextView stv_addtocart;
    private RelativeLayout rl_bottom;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, GoodDetailActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, GoodDetailActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, GoodDetailActivity.class);
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
        setContentView(R.layout.activity_gooddetial);
        initView();
    }

    private void initView() {
        titlebar = (TitleBar) findViewById(R.id.titlebar);
        stv_addtocart = (SuperTextView) findViewById(R.id.stv_addtocart);
        stv_addtocart.setOnClickListener(this);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stv_addtocart:
                showGoodAttrsAndNum(stv_addtocart);
                break;
        }
    }

    public void showGoodAttrsAndNum(View showParentView) {

        List<GoodAttrsBean> list = new ArrayList<>();
        List<String> childlist = new ArrayList<>();
        childlist.add("粉红色");
        childlist.add("红色");
        childlist.add("蓝色");
        childlist.add("粉红色");
        childlist.add("浅紫色");
        childlist.add("粉红色");
        childlist.add("红色");
        childlist.add("蓝色蓝色蓝色");
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));
        list.add(new GoodAttrsBean("颜色" , childlist));

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuView = layoutInflater.inflate(R.layout.popu_goodattrs, null);
        RecyclerView rv_attrs = (RecyclerView)popuView.findViewById(R.id.rv_attrs);
        rv_attrs.setLayoutManager(new LinearLayoutManager(this));
        rv_attrs.setAdapter(new XGoodAttrsAdapter(this , list));
        ImageView iv_cancel = (ImageView)popuView.findViewById(R.id.iv_cancel);
        //设置弹出窗口参数
        final PopupWindow popupWindow = new PopupWindow(popuView , WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
        //消去遮罩
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);

            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.showAtLocation(showParentView, Gravity.BOTTOM, 0, 0);
    }



}
