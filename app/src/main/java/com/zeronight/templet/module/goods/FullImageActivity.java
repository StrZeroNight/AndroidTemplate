package com.zeronight.templet.module.goods;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/10/10.
 */
public class FullImageActivity extends BaseActivity implements BGABanner.Adapter {

    private BGABanner bga_prodetial;
    private LinearLayout ll_root;
    private final static String IMAGE_LIST = "IMAGE_LIST";

    public static void start(Context context, ArrayList<String> circlepic) {
        Intent it = new Intent(context, FullImageActivity.class);
        it.putStringArrayListExtra(IMAGE_LIST, circlepic);
        context.startActivity(it);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getParcelableArrayListExtra(IMAGE_LIST) != null) {
            ArrayList<String> imageList = intent.getStringArrayListExtra(IMAGE_LIST);
            iniCirclePic(bga_prodetial, imageList);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);
        initView();
        initIntent();
    }

    private void initView() {
        bga_prodetial = (BGABanner) findViewById(R.id.bga_prodetial);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void iniCirclePic(BGABanner bgaBanner, ArrayList<String> circlepic) {
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();
        List<View> views = new ArrayList<>();
        //拼接图片url
        for (int i = 0; i < circlepic.size(); i++) {
            imageList.add(circlepic.get(i).toString());
            stringList.add("");
            views.add(new PhotoView(FullImageActivity.this));
        }
        //初始化数据
        bgaBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        bgaBanner.setAdapter(this);
        if (imageList.size() > 0 && stringList.size() > 0) {
            bgaBanner.setData(views, imageList, stringList);
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        PhotoView iv = (PhotoView) view;
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(FullImageActivity.this)
                .load(model)
                .into(iv);
    }


}
