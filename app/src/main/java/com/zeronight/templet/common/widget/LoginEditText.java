package com.zeronight.templet.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.utils.XStringUtils;


/**
 * 一般这样的自定义控件有很多种
 * 在代码里面登录注册的用的比较多，所以最基本的样式用登录注册的，其他的少量其他地方需要的就直接该参数处理
 * Created by Administrator on 2018/1/3.
 */
public class LoginEditText extends RelativeLayout {

    private TextView tv_icon;
    private EditText et_content;

    public LoginEditText(Context context) {
        this(context , null);
    }

    public LoginEditText(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    @SuppressLint("ResourceAsColor")
    public LoginEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_loginedittext, this, true);
        tv_icon = findViewById(R.id.tv_icon);
        et_content = findViewById(R.id.et_content);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoginEditText, defStyleAttr, 0);
        String hint = a.getString(R.styleable.LoginEditText_let_hint);
        int hintColor = a.getResourceId(R.styleable.LoginEditText_let_hintcolor, defStyleAttr);
        int contentType = a.getInt(R.styleable.LoginEditText_let_contenttype, defStyleAttr);
        int icon = a.getResourceId(R.styleable.LoginEditText_let_icon, defStyleAttr);

        if (!XStringUtils.isEmpty(hint)) {
            et_content.setHint(hint);
        }
        if (hintColor != 0) {
            et_content.setHintTextColor(hintColor);
        }
        if (icon != 0) {
            tv_icon.setBackgroundResource(icon);
        }
        if (contentType == 1) {
            et_content.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        }else if (contentType == 2){
            et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
        }else{
            et_content.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        a.recycle();

    }

    public String getContent(){
        return et_content.getText().toString();
    }

}
