package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zeronight.templet.R;
import com.zeronight.templet.common.dialog.TipDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 带删除的文本编辑框
 * <p>
 * Created by Administrator on 2016/6/6.
 */
public class OrderButton extends RelativeLayout {

    private LinearLayout ll_dafukuan;
    private SuperTextView stv_cancel;
    private SuperTextView stv_pay;
    private LinearLayout ll_daishouhuo;
    private SuperTextView stv_wuliu;
    private SuperTextView stv_shouhuo;
    private LinearLayout ll_yishouhuo;
    private SuperTextView stv_payback;
    private SuperTextView stv_wuliu2;
    private LinearLayout ll_yiwancheng;
    private SuperTextView stv_shanchu;
    private SuperTextView stv_payback2;
    private SuperTextView stv_wuliu3;
    private LinearLayout ll_daifahuo;
    private SuperTextView stv_tuikuan;
    private LinearLayout ll_shouhou;
    private LinearLayout ll_payback1;
    private LinearLayout ll_payback2;
    private SuperTextView stv_detial;
    private SuperTextView stv_detial1;
    private SuperTextView stv_write;
    private SuperTextView stv_detial2;
    //
    private final static int TYPE_DAIFUKUAN = 1;
    private final static int TYPE_DAISHOUHUO = 2;
    private final static int TYPE_YISHOUHUO = 3;
    private final static int TYPE_YIWANCHENG = 4;
    private final static int TYPE_DAIFAHUO = 5;
    private final static int TYPE_SHOUHOU = 6;
    //
    private List<LinearLayout> lists = new ArrayList<>();

    public OrderButton(Context context) {
        this(context, null);
    }

    public OrderButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderButton(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.weight_orderbutton, this, true);

        ll_dafukuan = (LinearLayout) findViewById(R.id.ll_dafukuan);
        stv_cancel = (SuperTextView) findViewById(R.id.stv_cancel);
        stv_pay = (SuperTextView) findViewById(R.id.stv_pay);
        ll_daishouhuo = (LinearLayout) findViewById(R.id.ll_daishouhuo);
        stv_wuliu = (SuperTextView) findViewById(R.id.stv_wuliu);
        stv_shouhuo = (SuperTextView) findViewById(R.id.stv_shouhuo);
        stv_payback = (SuperTextView) findViewById(R.id.stv_payback);
        ll_yishouhuo = (LinearLayout) findViewById(R.id.ll_yishouhuo);
        stv_wuliu2 = (SuperTextView) findViewById(R.id.stv_wuliu2);
        ll_yiwancheng = (LinearLayout) findViewById(R.id.ll_yiwancheng);
        stv_shanchu = (SuperTextView) findViewById(R.id.stv_shanchu);
        stv_payback2 = (SuperTextView) findViewById(R.id.stv_payback2);
        stv_wuliu3 = (SuperTextView) findViewById(R.id.stv_wuliu3);
        ll_daifahuo = (LinearLayout) findViewById(R.id.ll_daifahuo);
        stv_tuikuan = (SuperTextView) findViewById(R.id.stv_tuikuan);
        ll_shouhou = (LinearLayout) findViewById(R.id.ll_shouhou);
        stv_detial = (SuperTextView) findViewById(R.id.stv_detial);
        ll_payback1 = (LinearLayout) findViewById(R.id.ll_payback1);
        stv_detial1 = (SuperTextView) findViewById(R.id.stv_detial1);
        stv_write = (SuperTextView) findViewById(R.id.stv_write);
        ll_payback2 = (LinearLayout) findViewById(R.id.ll_payback2);
        stv_detial2 = (SuperTextView) findViewById(R.id.stv_detial2);
        lists.add(ll_dafukuan);
        lists.add(ll_daishouhuo);
        lists.add(ll_yishouhuo);
        lists.add(ll_yiwancheng);
        lists.add(ll_daifahuo);
        lists.add(ll_shouhou);
        lists.add(ll_payback1);
        lists.add(ll_payback2);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OrderButton, defStyleAttr, 0);
        int orderType = a.getInt(R.styleable.OrderButton_order_type, 0);
        show(orderType);
        a.recycle();

        stv_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new TipDialog(context, "是否取消订单", new TipDialog.DialogButtonClick() {
                    @Override
                    public void onDismiss() {

                    }

                    @Override
                    public void onSure() {

                    }
                });
            }
        });

        stv_pay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_wuliu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_shouhuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new TipDialog(context, "是否确认收货", new TipDialog.DialogButtonClick() {
                    @Override
                    public void onDismiss() {

                    }

                    @Override
                    public void onSure() {

                    }
                });
            }
        });

        stv_wuliu2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_shanchu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new TipDialog(context, "是否删除该订单", new TipDialog.DialogButtonClick() {
                    @Override
                    public void onDismiss() {

                    }

                    @Override
                    public void onSure() {

                    }
                });
            }
        });

        stv_payback2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_payback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_wuliu3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_tuikuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_detial.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //物流详情
        stv_detial1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //物流详情
        stv_detial2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stv_write.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void show(int showPosition){
        for (int i = 0; i < lists.size(); i++) {
            if (showPosition == i){
                lists.get(i).setVisibility(VISIBLE);
            }else{
                lists.get(i).setVisibility(GONE);
            }
        }
    }

    public void setOrderType(int orderType){
        show(orderType);
    }

}
