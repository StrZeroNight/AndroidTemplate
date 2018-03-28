package com.zeronight.templet.module.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.PermissionUtils;
import com.zeronight.templet.common.utils.camera.ImageChoose;
import com.zeronight.templet.common.widget.ArrorText;

/**
 * Created by Administrator on 2018/1/4.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_pic;
    private RelativeLayout rl_image;
    private ArrorText at_nike;
    private ArrorText at_gender;
    private ArrorText at_phone;
    private ArrorText at_age;
    private ArrorText at_area;

    public static void start(Context context) {
        Intent it = new Intent(context, UserInfoActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initCarme();
        initView();
    }

    private PermissionUtils permissionUtils;
    private ImageChoose imageChoose;
    private void initCarme() {
        permissionUtils = new PermissionUtils(UserInfoActivity.this);
        imageChoose = new ImageChoose(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChoose.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        rl_image = (RelativeLayout) findViewById(R.id.rl_image);
        rl_image.setOnClickListener(this);
        at_nike = (ArrorText) findViewById(R.id.at_nike);
        at_nike.setOnClickListener(this);
        at_gender = (ArrorText) findViewById(R.id.at_gender);
        at_gender.setOnClickListener(this);
        at_phone = (ArrorText) findViewById(R.id.at_phone);
        at_phone.setOnClickListener(this);
        at_age = (ArrorText) findViewById(R.id.at_age);
        at_age.setOnClickListener(this);
        at_area = (ArrorText) findViewById(R.id.at_area);
        at_area.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_image:
                permissionUtils.registerPermissionListener(new PermissionUtils.IPermissionFinish() {
                    @Override
                    public void permissionSuccess() {
                        imageChoose.showChooseCarme(rl_image);
                        imageChoose.setOnGetImageListener(new ImageChoose.OnGetImageListener() {
                            @Override
                            public void onImageGet(String data) {
                                // TODO: 2018/1/25 解析data图片内容 放到glide里
                            }
                        });
                    }
                });
                String[] premission = {PermissionUtils.WRITE_EXTERNAL, PermissionUtils.CAMERA};
                permissionUtils.askActivityPermission(premission, PermissionUtils.REQUEST_CODE);
                break;
            case R.id.at_nike:
                ChangeInfoActivity.start(this , "小七" , "修改昵称");
                break;
            case R.id.at_gender:
                showChooseGender(at_gender);
                break;
            case R.id.at_phone:
                break;
            case R.id.at_age:
                break;
            case R.id.at_area:
                break;
        }
    }

    /**
     * 选择性别
     */
    public void showChooseGender(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuView = layoutInflater.inflate(R.layout.popu_gender, null);
        //所有按钮的监听事件
        TextView tv_gender_men = (TextView) popuView.findViewById(R.id.tv_men);
        TextView tv_gender_women = (TextView) popuView.findViewById(R.id.tv_women);
        TextView tv_cancel_popuwindow = (TextView) popuView.findViewById(R.id.tv_cancel);
        final PopupWindow popupWindow = new PopupWindow(popuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //点击事件
        genderClick(tv_gender_men, tv_gender_women, popupWindow);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //消去遮罩
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);

            }
        });
        //监听取消按钮
        tv_cancel_popuwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 50);
    }

    public void genderClick(TextView tv_gender_men, TextView tv_gender_women, final PopupWindow popupWindow) {
        tv_gender_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGender("1");
                popupWindow.dismiss();
            }
        });
        tv_gender_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGender("2");
                popupWindow.dismiss();
            }
        });
    }

    private void changeGender(String gender) {
        showprogressDialogCanNotCancel();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
//                .setUrl(ConstantUrl.login)
//                .setParams("phone", phone)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                dismissProgressDialog();
//                UserInfoBean userInfoBean = JSON.parseObject(data, UserInfoBean.class);
            }

            @Override
            public void onNoData() {
                dismissProgressDialog();
            }

            @Override
            public void onServerError() {
                dismissProgressDialog();
            }
        });
    }


}
