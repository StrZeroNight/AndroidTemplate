package com.zeronight.templet.common.widget.vercode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zeronight.templet.R;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;


/**
 * 一般这样的自定义控件有很多种
 * 在代码里面登录注册的用的比较多，所以最基本的样式用登录注册的，其他的少量其他地方需要的就直接该参数处理
 * Created by Administrator on 2018/1/3.
 */
public class LoginEditTextImage extends RelativeLayout {

    private ImageView iv_icon;
    private EditText et_content;
    private TextView stv_getvercode;

    public LoginEditTextImage(Context context) {
        this(context, null);
    }

    public LoginEditTextImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ResourceAsColor")
    public LoginEditTextImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.widget_veriamge, this, true);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        et_content = (EditText) findViewById(R.id.et_content);
        stv_getvercode = (TextView) findViewById(R.id.stv_getver_image);
        initClick();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoginEditText, defStyleAttr, 0);
        String hint = a.getString(R.styleable.LoginEditText_let_hint);
        int hintColor = a.getResourceId(R.styleable.LoginEditText_let_hintcolor, defStyleAttr);
        int contentType = a.getInt(R.styleable.LoginEditText_let_contenttype, defStyleAttr);
        int icon = a.getResourceId(R.styleable.LoginEditText_let_icon, defStyleAttr);

        if (!XStringUtils.isEmpty(hint)) {
            et_content.setHint(hint);
        }
        if (hintColor != 0) { // TODO: 2018/1/3 no use
            et_content.setHintTextColor(hintColor);
        }
        if (icon != 0) {
            iv_icon.setImageResource(icon);
        }
        if (contentType == 1) {
            et_content.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        } else if (contentType == 2) {
            et_content.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        } else {
            et_content.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        a.recycle();

    }

    private void initClick() {
        stv_getvercode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = et_content.getText().toString();
                if (XStringUtils.isEmpty(phone)) {
                    ToastUtils.showMessage("手机号不能为空");
                    return;
                }
                if (!XStringUtils.checkPhoneNum(phone)) {
                    ToastUtils.showMessage("手机号不是标准手机号");
                    return;
                }
                getVerCode(et_content.getText().toString());
            }
        });
    }

    public String getContent() {
        return et_content.getText().toString();
    }

    private void getVerCode(final String phone) {
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("phone", phone)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                ToastUtils.showMessage("验证码发送失败");
            }

            @Override
            public void onSuccess(String data) {
                //成功发送以后在开始计时
                MsgCodeBean msgCodeBean = JSON.parseObject(data, MsgCodeBean.class);
                getVerCode2(phone , msgCodeBean.getCode());
            }

            @Override
            public void onNoData() {
                ToastUtils.showMessage("验证码发送失败");
            }

            @Override
            public void onServerError() {
            }
        });
    }

    private void getVerCode2(String phone , String msgCode) {
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("phone", phone)
                .setParams("code", msgCode)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                ToastUtils.showMessage("验证码发送失败");
            }

            @Override
            public void onSuccess(String data) {
                ToastUtils.showMessage("验证码发送成功");
                showCountDownTimer(stv_getvercode);
            }

            @Override
            public void onNoData() {
                ToastUtils.showMessage("验证码发送失败");
            }

            @Override
            public void onServerError() {
            }
        });
    }

    //验证码计时器
    private static CountDownTimer countDownTimer;
    public static void showCountDownTimer(final TextView tv_time) {
        showCountDownTimer(tv_time , "秒后重发");
    }

    public static void showCountDownTimer(final TextView tv_time , final String content) {

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setClickable(false);
                tv_time.setText(millisUntilFinished / 1000 + content);
            }

            @Override
            public void onFinish() {
                tv_time.setClickable(true);
                tv_time.setText("重新获取验证码");
            }
        };
        countDownTimer.start();

    }


    public static void stopCountDownTimer(TextView tv_time){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        tv_time.setClickable(true);
        tv_time.setText("获取验证码");
    }

    public void isShowVerCode(boolean isShow){
        if (isShow) {
            stv_getvercode.setVisibility(VISIBLE);
        }else{
            stv_getvercode.setVisibility(GONE);
        }
    }

}
