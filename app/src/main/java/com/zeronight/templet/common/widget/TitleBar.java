package com.zeronight.templet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
public class TitleBar extends RelativeLayout {

    private LinearLayout ll_right;
    private TextView tv_right;
    private ImageView iv_right;
    private TextView tv_title;
    private RelativeLayout titlebar;
    private LinearLayout ll_back;
    private TitlebarClick onTitlebarClickListener;

    public TitleBar(Context context) {
        this(context , null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public TitleBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_titlebar, this, true);
        titlebar = (RelativeLayout) findViewById(R.id.titlebar);
        ll_right = (LinearLayout) findViewById(R.id.ll_right);
        tv_right = (TextView) findViewById(R.id.tv_right);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
        String title = a.getString(R.styleable.TitleBar_btitle);
        final String content = a.getString(R.styleable.TitleBar_bright_text);
        int image = a.getResourceId(R.styleable.TitleBar_bright_image , defStyleAttr);
        boolean isShowBack = a.getBoolean(R.styleable.TitleBar_bishow_back , true);
        boolean isShowBg = a.getBoolean(R.styleable.TitleBar_bisshow_bg , true);

        if (!isShowBg) {
            titlebar.setBackgroundColor(0);
        }
        if (title != null && !TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }
        if (content != null && !TextUtils.isEmpty(content)) {
            tv_right.setText(content);
            tv_right.setVisibility(VISIBLE);
            iv_right.setVisibility(GONE);
        }
        if (image != 0) {
            iv_right.setImageResource(image);
            tv_right.setVisibility(GONE);
            iv_right.setVisibility(VISIBLE);
        }
        if (isShowBack) {
            ll_back.setVisibility(VISIBLE);
        }else{
            ll_back.setVisibility(GONE);
        }

        ll_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitlebarClickListener != null) {
                    onTitlebarClickListener.onRightClick();
                }
            }
        });

        ll_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                appCompatActivity.finish();
                if (onTitlebarClickListener != null) {
                    onTitlebarClickListener.onBackClick();
                }
            }
        });

        a.recycle();

    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public interface TitlebarClick{
        void onRightClick();
        void onBackClick();
    }

    public void setOnTitlebarClickListener(TitlebarClick onTitlebarClickListener){
        this.onTitlebarClickListener = onTitlebarClickListener;
    }

}
