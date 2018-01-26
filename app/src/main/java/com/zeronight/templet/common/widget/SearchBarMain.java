package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/8.
 */

public class SearchBarMain extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_content;
    private ImageView iv_icon;
    private ImageView iv_arror;

    public SearchBarMain(Context context) {
        this(context , null);
    }

    public SearchBarMain(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SearchBarMain(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_searchbar_main, this, true);
        EditText et_search = (EditText)findViewById(R.id.et_search);
        ImageView iv_left = (ImageView)findViewById(R.id.iv_left);
        ImageView iv_right = (ImageView)findViewById(R.id.iv_right);
        RelativeLayout rl_root = (RelativeLayout)findViewById(R.id.rl_root);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchBarMain, defStyleAttr, 0);
        int leftIcon = a.getResourceId(R.styleable.SearchBarMain_left_icon, 0);
        int rightIcon = a.getResourceId(R.styleable.SearchBarMain_right_icon, 0);
        int background = a.getResourceId(R.styleable.SearchBarMain_sbackground, 0);
        String hint = a.getString(R.styleable.SearchBarMain_shint);
        //左icon
        if (leftIcon == 0) {
            iv_left.setVisibility(INVISIBLE);
        }else{
            iv_left.setVisibility(VISIBLE);
            iv_left.setImageResource(leftIcon);
        }
        //右icon
        if (rightIcon == 0) {
            iv_right.setVisibility(INVISIBLE);
        }else{
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rightIcon);
        }
        //searchbar背景
        rl_root.setBackgroundResource(background);
        //hint
        et_search.setHint(hint);

        a.recycle();

    }



}
