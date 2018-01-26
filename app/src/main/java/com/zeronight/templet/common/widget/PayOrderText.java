package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/8.
 */

public class PayOrderText extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_content;

    public PayOrderText(Context context) {
        this(context , null);
    }

    public PayOrderText(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public PayOrderText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_payordertext, this, true);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArrorText, defStyleAttr, 0);
        String title = a.getString(R.styleable.ArrorText_atitle);
        String content = a.getString(R.styleable.ArrorText_acontent);
        int icon = a.getResourceId(R.styleable.ArrorText_aicon , defStyleAttr);
        int content_color = a.getColor(R.styleable.ArrorText_acontent_color , defStyleAttr);
        boolean isshow_content = a.getBoolean(R.styleable.ArrorText_aishow_content , true);

        if(title != null && !TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if(content != null && !TextUtils.isEmpty(content)){
            tv_content.setText(content);
        }
        if (content_color != 0) {
            tv_content.setTextColor(content_color);
        }
        if (isshow_content) {
            tv_content.setVisibility(VISIBLE);
        }else{
            tv_content.setVisibility(GONE);
        }

        a.recycle();

    }

    public void setContent(String greenTitle) {
        tv_content.setText(greenTitle);
    }

    public String getContent() {
        return tv_content.getText().toString();
    }

    public void setTitle(String mainTitle) {
        tv_title.setText(mainTitle);
    }

    public String getTitle() {
        return tv_title.getText().toString();
    }

}
