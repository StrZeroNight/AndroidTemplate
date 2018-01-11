package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/8.
 */

public class SwitchText extends RelativeLayout {

    private TextView tv_title;
    private ImageView iv_icon;
    private Switch switchs;

    public SwitchText(Context context) {
        this(context , null);
    }

    public SwitchText(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SwitchText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_switchtext, this, true);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
        switchs = (Switch) findViewById(R.id.switchs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArrorText, defStyleAttr, 0);
        String title = a.getString(R.styleable.ArrorText_atitle);
        int icon = a.getResourceId(R.styleable.ArrorText_aicon , defStyleAttr);
        boolean isshow_icon = a.getBoolean(R.styleable.ArrorText_aishow_icon , true);

        if(title != null && !TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if(icon != 0){
            iv_icon.setImageResource(icon);
        }
        if (isshow_icon) {
            iv_icon.setVisibility(VISIBLE);
        }else{
            iv_icon.setVisibility(GONE);
        }

        a.recycle();

    }

    public void setTitle(String mainTitle) {
        tv_title.setText(mainTitle);
    }

    public String getTitle() {
        return tv_title.getText().toString();
    }

    public void setSwitchTrue(){
        switchs.setChecked(true);
    }

    public void setSwitchFalse(){
        switchs.setChecked(false);
    }

    public boolean getSwitchStatus(){
        return switchs.isChecked();
    }

}
