package com.zeronight.templet.common.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zeronight.templet.R;
import com.zeronight.templet.common.retrofithttp.CommenMethod;


/**
 * base activity
 * controll progress
 * <p/>
 * Created by Administrator on 2016/7/11.
 */
public abstract class BaseActivity extends AppCompatActivity {

    Dialog progressDialog;
    protected CommenMethod commenMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commenMethod = new CommenMethod(this);
        initProgressDialog();
    }

    private void initProgressDialog(){
        progressDialog = new Dialog(BaseActivity.this, R.style.dialog_progress);
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void showprogressDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public void showprogressDialogCanNotCancel() {
        if (progressDialog != null) {
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void showprogressTransparentDialog() {
        if (progressDialog != null) {
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
        super.onDestroy();
    }

    //机械返回键控制
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//        }
//        return false;
//    }


}
