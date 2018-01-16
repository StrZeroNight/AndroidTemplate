package com.zeronight.templet.common.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 版本更新工具类
 */
public class UpdateManager {

    private Context mContext; //上下文

    private String apkUrl = "";
//    private static final String savePath = "/sdcard/tianhongAPK/"; //apk保存到SD卡的路径
//    private static final String saveFileName = savePath + "tianhong.apk"; //完整路径名
    private static final String savePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/"; //apk保存到SD卡的路径
    private static final String saveFileName = savePath + "updateApk.apk"; //完整路径名

    private ProgressBar mProgress; //下载进度条控件
    private static final int DOWNLOADING = 1; //表示正在下载
    private static final int DOWNLOADED = 2; //下载完毕
    private static final int DOWNLOAD_FAILED = 3; //下载失败
    public static final String TYPE_FORCE = "1"; //强制下载
    public static final String TYPE_NOFORCE = "2"; //非强制下载
    private int progress; //下载进度
    private boolean cancelFlag = false; //取消下载标志位

    private String updateDescription = "软件有新版本需要更新，请点击现在更新"; //更新内容描述信息
    private boolean forceUpdate = false; //是否强制更新

    private AlertDialog noticeDialog, downloadDialog; //表示提示对话框、进度条对话框

    /**
     * 是否强制更新应该是后台传递数据处理
     */
    public UpdateManager(Context context, String type, String url, String content) {
        this.mContext = context;
        this.apkUrl = url;
        if (TYPE_FORCE.equals(type)) {
            forceUpdate = false;
        } else if (TYPE_NOFORCE.equals(type)) {
            forceUpdate = true;
        }
        if (!TextUtils.isEmpty(content)) {
            updateDescription = content;
        }
    }

    public UpdateManager(Context context, String url, String content) {
        this.mContext = context;
        this.apkUrl = url;
        if (!TextUtils.isEmpty(content)) {
            updateDescription = content;
        }
    }

    public UpdateManager(Context context, String url) {
        this.mContext = context;
        this.apkUrl = url;
    }

    /**
     * 判断版本号
     */
    public void judgeVerCode(String mVersionCode){
        if (!XStringUtils.isEmpty(mVersionCode) && XStringUtils.isStringAreNum(mVersionCode)) {
            int mVersionCodeInt = Integer.parseInt(mVersionCode);
            judgeVerCode(mVersionCodeInt);
        }
    }

    public void judgeVerCode(int mVersionCodeInt){
        int versionCode = getVersionCode(mContext);
        if (mVersionCodeInt > versionCode) {
            showNoticeDialog();
        }else{
            ToastUtils.showMessage("当前已是最新版本");
        }
    }

    /**
     * 获取版本编号
     */
    private int getVersionCode(Context context)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 显示更新对话框
     */
    private void showNoticeDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("更新提示");
        dialog.setMessage(updateDescription);
        dialog.setPositiveButton("现在更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }
        });
        //是否强制更新
        if (forceUpdate == false) {
            dialog.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        noticeDialog = dialog.create();
        noticeDialog.setCancelable(false);
        noticeDialog.show();

    }

    /**
     * 显示进度条对话框
     */
    private void showDownloadDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
//        显示更新进度
        dialog.setView(v);
        //如果是强制更新，则不显示取消按钮
        if (forceUpdate == false) {
            dialog.setNegativeButton("取消更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    cancelFlag = false;
                }
            });
        }
        downloadDialog = dialog.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        //下载apk
        downloadAPK();

    }

    /**
     * 下载apk的线程
     */
    private void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    String carrier= android.os.Build.MANUFACTURER;
                    String model= android.os.Build.MODEL;
                    Logger.i("carrier:" + carrier);
                    Logger.i("model:" + model);

                    if (model.contains("AL")) {

                    }else{

                    }
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkFile = saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[1024];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        //更新进度  
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            //下载完成通知安装  
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); //点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 更新UI的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOADED:
                    if (downloadDialog != null)
                        downloadDialog.dismiss();
                    installAPKAuto();
                    break;
                case DOWNLOAD_FAILED:
                    Toast.makeText(mContext, "下载失败，请稍后再试", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };


    private void installAPKAuto() {
        File apkFile = new File(saveFileName);
        Logger.i("installAPKAuto:" + apkFile.getAbsolutePath());
        if (!apkFile.exists()) {
            return;
        }
        // 7.0以上需要从外部安装需要用fileprovider处理
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (android.os.Build.VERSION.SDK_INT < 24) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
        } else {
            //改变Uri com.fanqie.fileprovider注意和xml中的一致
            Uri uri = FileProvider.getUriForFile(mContext, "com.fanqie.templet.fileprovider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }


}