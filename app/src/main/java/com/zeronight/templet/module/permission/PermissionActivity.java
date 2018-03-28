package com.zeronight.templet.module.permission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.PermissionUtils;
import com.zeronight.templet.common.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/10.
 */

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_permission;
    private Button btn_download;
    private PermissionUtils permissionUtils;

    public static void start(Context context) {
        Intent it = new Intent(context, PermissionActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    private void initView() {
        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_permission.setOnClickListener(this);
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_permission:
                permissionUtils = new PermissionUtils(PermissionActivity.this);
                permissionUtils.registerPermissionListener(new PermissionUtils.IPermissionFinish() {
                    @Override
                    public void permissionSuccess() {
                        ToastUtils.showMessage("获取权限成功");
                    }
                });
                String[] premission = {PermissionUtils.WRITE_EXTERNAL, PermissionUtils.CAMERA};
                permissionUtils.askActivityPermission(premission, PermissionUtils.REQUEST_CODE);
                break;
            case R.id.btn_download:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
