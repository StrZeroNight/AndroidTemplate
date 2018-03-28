package com.zeronight.templet.common.utils;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeronight.templet.R;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;

/**
 * Created by Administrator on 2017/10/24.
 */

public class VerCodeUtils {

    private AppCompatActivity appCompatActivity;
    public final static String GET_VERCODE_REGISTER = "1";
    public final static String GET_VERCODE_FINDPASSWORD = "2";
    private static CountDownTimer countDownTimer;

    public VerCodeUtils(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void showImageCode(final String phone, final TextView tv_get_vercode) {
        if (XStringUtils.isEmpty(phone)) {
            ToastUtils.showMessage("手机号不能为空");
            return;
        }
        if (!XStringUtils.checkPhoneNum(phone)) {
            ToastUtils.showMessage("手机号不是标准手机号");
            return;
        }
//        phone	是	string	手机号
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("phone", phone)
                .setParams("type", "2")//2是找回密码
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
//                ImageCodeBean imageCodeBean = JSON.parseObject(data, ImageCodeBean.class);
//                String path = imageCodeBean.getPath();
//                showImageDialog(phone, tv_get_vercode , path);
            }

            @Override
            public void onNoData() {

            }

            @Override
            public void onServerError() {

            }
        });

    }

    private void showImageDialog(final String phone, final TextView tv_get_vercode , String path) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);
        final AlertDialog alertDialog = builder.create();
        View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.dialog_imagecode, null, false);
        final EditText et_image_code = (EditText) view.findViewById(R.id.et_image_code);
        final ImageView iv_image_code = (ImageView) view.findViewById(R.id.iv_image_code);
        TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        Glide.with(appCompatActivity).load(CommonUrl.IMAGE_URL + path).into(iv_image_code);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageCode = et_image_code.getText().toString();
                if (XStringUtils.isEmpty(imageCode)) {
                    ToastUtils.showMessage("图片验证码不能为空");
                    return;
                }
                getVerCode(phone, imageCode, GET_VERCODE_FINDPASSWORD, tv_get_vercode , alertDialog);
            }
        });
        iv_image_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageCodeAgain(phone , iv_image_code);
            }
        });

//        Window window = alertDialog.getWindow();
//        window.setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setView(view);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                CommonUtils.hideSoft(appCompatActivity, et_image_code);
            }
        });
        alertDialog.show();
        CommonUtils.showSoft(appCompatActivity, et_image_code);

    }

    public static void getVerCode(String phone , String imageCode , String type , final TextView tv_get_vercode , final AlertDialog alertDialog) {
//        type	是	string	1是注册 2找回密码
//        phone	是	string	手机号
//        img_code	是	string	图片验证码
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("type", type)
                .setParams("phone", phone)
                .setParams("img_code", imageCode)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                showCountDownTimer(tv_get_vercode, "s");
                ToastUtils.showMessage("短信验证码发送成功");
            }

            @Override
            public void onNoData() {
            }

            @Override
            public void onServerError() {
            }
        });

    }

    public void getImageCodeAgain(final String phone , final ImageView iv_image_code) {
//        phone	是	string	手机号
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("phone", phone)
                .setParams("type", "2")//2是找回密码
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
//                ImageCodeBean imageCodeBean = JSON.parseObject(data, ImageCodeBean.class);
//                String path = imageCodeBean.getPath();
//                Glide.with(appCompatActivity).load(ConstantUrl.imageUrl + path).into(iv_image_code);
            }

            @Override
            public void onNoData() {

            }

            @Override
            public void onServerError() {

            }
        });

    }

    //验证码计时器
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
                tv_time.setText("获取验证码");
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


}
