package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/11/8.
 */

public class SearchBarClick extends RelativeLayout {

    public SearchBarClick(Context context) {
        this(context, null);
    }

    public SearchBarClick(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBarClick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_searchbarclick, this, true);
        ImageView iv_left = (ImageView) findViewById(R.id.iv_left);
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        LinearLayout ll_search = (LinearLayout) findViewById(R.id.ll_search);
        TextView tv_search = (TextView) findViewById(R.id.tv_search);
        ImageView iv_background = (ImageView) findViewById(R.id.iv_background);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchBarClick, defStyleAttr, 0);
        int leftIcon = a.getResourceId(R.styleable.SearchBarClick_sbc_left_icon, 0);
        int rightIcon = a.getResourceId(R.styleable.SearchBarClick_sbc_right_icon, 0);
        int background = a.getResourceId(R.styleable.SearchBarClick_sbc_background, 0);
        String hint = a.getString(R.styleable.SearchBarClick_sbc_hint);

        //hint
        tv_search.setText(hint);

        //左icon
        if (leftIcon == 0) {
            iv_left.setVisibility(INVISIBLE);
        } else {
            iv_left.setVisibility(VISIBLE);
            iv_left.setImageResource(leftIcon);
        }

        //右icon
        if (rightIcon == 0) {
            iv_right.setVisibility(INVISIBLE);
        } else {
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rightIcon);
        }

        //searchbar背景
        iv_background.setBackgroundResource(background);

        //点击事件
        ll_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickSearchClickListener != null) {
                    onClickSearchClickListener.OnSearchClicke();
                }
            }
        });

        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickSearchClickListener != null) {
                    onClickSearchClickListener.OnLeftClick();
                }
            }
        });

        iv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickSearchClickListener != null) {
                    onClickSearchClickListener.OnRightClick();
                }
            }
        });

        a.recycle();

    }

    private OnClickSearchClickListener onClickSearchClickListener;
    public interface OnClickSearchClickListener {
        void OnSearchClicke();
        void OnLeftClick();
        void OnRightClick();
    }

    public void setOnClickSearchClickListener(OnClickSearchClickListener onClickSearchClickListener) {
        this.onClickSearchClickListener = onClickSearchClickListener;
    }

}
