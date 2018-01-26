package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/10.
 */

public class PayChoose extends LinearLayout{

    private PayChooseListener payChooseListener;

    public PayChoose(Context context) {
        this(context , null);
    }

    public PayChoose(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public PayChoose(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_paychoose, this, true);
        RelativeLayout rl_wx = (RelativeLayout) findViewById(R.id.rl_wx);
        final ImageView iv_choose_wx = (ImageView) findViewById(R.id.iv_choose_wx);
        RelativeLayout rl_zfb = (RelativeLayout) findViewById(R.id.rl_zfb);
        final ImageView iv_choose_zfb = (ImageView) findViewById(R.id.iv_choose_zfb);
        RelativeLayout rl_ye = (RelativeLayout) findViewById(R.id.rl_ye);
        final ImageView iv_choose_ye = (ImageView) findViewById(R.id.iv_choose_ye);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArrorText, defStyleAttr, 0);

        rl_wx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payChooseListener != null) {
                    payChooseListener.onWxChoose();
                    changeChooseButton(iv_choose_wx , iv_choose_ye , iv_choose_zfb);
                }
            }
        });

        rl_zfb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payChooseListener != null) {
                    payChooseListener.onZfbChoose();
                    changeChooseButton(iv_choose_zfb , iv_choose_ye , iv_choose_wx);
                }
            }
        });

        rl_ye.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payChooseListener != null) {
                    payChooseListener.onYeChoose();
                    changeChooseButton(iv_choose_ye , iv_choose_wx , iv_choose_zfb);
                }
            }
        });

        a.recycle();

    }

    private void changeChooseButton(ImageView ivChoose , ImageView...ivNones){
        for (ImageView ivNon : ivNones) {
            ivNon.setBackgroundColor(Color.BLACK);
        }
        ivChoose.setBackgroundColor(Color.RED);
    }

    public interface PayChooseListener{
        void onWxChoose();
        void onZfbChoose();
        void onYeChoose();
    }

    public void setPayChooseListener(PayChooseListener payChooseListener){
        this.payChooseListener = payChooseListener;
    }

}
