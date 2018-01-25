package com.zeronight.templet.common.utils.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ImageChoose {

    private BaseActivity activity;
    ImageGetUtils imageGetUtils;
    private OnGetImageListener onGetImageListener;

    public ImageChoose(BaseActivity activity) {
        this.activity = activity;
        imageGetUtils = new ImageGetUtils(activity);
    }

    public void showChooseCarme(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuView = layoutInflater.inflate(R.layout.popup_carme, null);
        //所有按钮的监听事件
        TextView tv_carme_popuwindow = (TextView) popuView.findViewById(R.id.tv_carme);
        TextView tv_image_popuwindow = (TextView) popuView.findViewById(R.id.tv_image);
        TextView tv_cancel_popuwindow = (TextView) popuView.findViewById(R.id.tv_cancel);
        final PopupWindow popupWindow = new PopupWindow(popuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //点击事件
        clickListener(tv_carme_popuwindow, tv_image_popuwindow, popupWindow);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.3f;
        activity.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //消去遮罩
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);

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

    /**
     * popuwindows 点击事件
     */
    private void clickListener(TextView tv_carme, TextView tv_image, final PopupWindow popupWindow) {
        tv_carme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageGetUtils.getImageFromCamera();
                popupWindow.dismiss();
            }
        });
        tv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageGetUtils.getImageFromGallery();
                popupWindow.dismiss();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = imageGetUtils.ProcessResults(requestCode, resultCode, data);
        if (!XStringUtils.isEmpty(path)) {
            File compress = BitMapCompressUtils.compress(path, 500, 500, 20000);
            String compressPath = compress.getPath();
            updateImage(compressPath);
        }
    }

    private void updateImage(String paths) {
        activity.showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl("")
                .build();
        retrofitUtils.update(new File(paths), "", new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                activity.dismissProgressDialog();
                ToastUtils.showMessage("图片上传失败，请重新尝试");
            }

            @Override
            public void onSuccess(String data) {
                activity.dismissProgressDialog();
                onGetImageListener.onImageGet(data);
            }

            @Override
            public void onNoData() {
                activity.dismissProgressDialog();
                ToastUtils.showMessage("图片上传失败，请重新尝试");
            }

            @Override
            public void onServerError() {
                activity.dismissProgressDialog();
                ToastUtils.showMessage("图片上传失败，请重新尝试");
            }
        });
    }

    public interface OnGetImageListener{
        void onImageGet(String data);
    }

    public void setOnGetImageListener(OnGetImageListener onGetImageListener){
        this.onGetImageListener = onGetImageListener;
    }

}
