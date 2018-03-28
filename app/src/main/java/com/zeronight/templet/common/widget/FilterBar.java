package com.zeronight.templet.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/11/8.
 */
public class FilterBar extends RelativeLayout {

    private TextView tv_all;
    private TextView tv_sale;
    private TextView tv_price;
    private List<TextView> tv_hides = new ArrayList<>();
    private int priceStatus = 0;

    private FilterbarClick filterbarClick;

    public FilterBar(Context context) {
        this(context , null);
    }

    public FilterBar(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public FilterBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_filterbar, this, true);
        tv_all = findViewById(R.id.tv_all);
        tv_sale = findViewById(R.id.tv_sale);
        tv_price = findViewById(R.id.tv_price);
        tv_hides.add(tv_all);
        tv_hides.add(tv_sale);
        tv_hides.add(tv_price);

        tv_all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceStatus = 0;
                priceNonr();
                show(context , 0);
                if (filterbarClick != null) {
                    filterbarClick.onFirstClick();
                }
            }
        });

        tv_sale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                priceStatus = 0;
                priceNonr();
                show(context , 1);
                if (filterbarClick != null) {
                    filterbarClick.onSecondClick();
                }
            }
        });

        tv_price.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改状态，方便下面代码显示
                if (priceStatus == 0) {
                    priceStatus = 1;
                }else if (priceStatus == 1){
                    priceStatus = 2;
                }else if (priceStatus == 2){
                    priceStatus = 1;
                }

                if (priceStatus == 0) {
                    priceDown();
                }else if (priceStatus == 1){
                    priceUp();
                }else if (priceStatus == 2){
                    priceDown();
                }

                show(context , 2);
                if (filterbarClick != null) {
                    filterbarClick.onThirdClick();
                }
            }
        });

    }

    private void priceDown() {
        tv_price.setCompoundDrawablesWithIntrinsicBounds(0 , 0, R.drawable.price_down , 0);
    }

    private void priceUp() {
        tv_price.setCompoundDrawablesWithIntrinsicBounds(0 , 0, R.drawable.price_up , 0);
    }

    private void priceNonr() {
        tv_price.setCompoundDrawablesWithIntrinsicBounds(0 , 0, R.drawable.price , 0);
    }

    public interface FilterbarClick{
        void onFirstClick();
        void onSecondClick();
        void onThirdClick();
    }

    public void setOnFilterbarClickListener(FilterbarClick filterbarClick){
        this.filterbarClick = filterbarClick;
    }

    private void show(Context context , int position){
        if (tv_hides.size() > 0) {
            for (int i = 0; i < tv_hides.size(); i++) {
                if (i == position) {
                    tv_hides.get(i).setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }else{
                    tv_hides.get(i).setTextColor(context.getResources().getColor(R.color.color_33));
                }
            }
        }else{
            ToastUtils.showMessage("初始化未完成");
        }
    }

}
