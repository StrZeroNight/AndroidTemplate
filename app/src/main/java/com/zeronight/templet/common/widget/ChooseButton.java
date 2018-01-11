package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/23.
 */

public class ChooseButton extends LinearLayout {

    private ImageView iv_icon;
    private int choose;
    private int none;

    public ChooseButton(Context context) {
        this(context , null);
    }

    public ChooseButton(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ChooseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_choose_button, this, true);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        TextView tv_name = (TextView) findViewById(R.id.tv_name);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChooseButton, defStyleAttr, 0);
        String name = a.getString(R.styleable.ChooseButton_choosebutton_name);
        tv_name.setText(name);
        choose = a.getResourceId(R.styleable.ChooseButton_choosebutton_choose , defStyleAttr);
        none = a.getResourceId(R.styleable.ChooseButton_choosebutton_none , defStyleAttr);
        iv_icon.setImageResource(none);

    }

    public void setChoose(){
        iv_icon.setImageResource(choose);
    }

    public void setNone(){
        iv_icon.setImageResource(none);
    }


}
