package com.zeronight.templet.common.utils;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限工具类
 * <p>
 * 申请获取权限
 * premissionUtils = new PremissionUtils(MainActivity.this);
 * premissionUtils.registerPermissionListener(new PremissionUtils.IPermissionFinish() {
 *
 * @Override public void permissionSuccess() {
 * Log.i("log", "--success--");
 * }
 * });
 * String[] premission = { PremissionUtils.WRITE_EXTERNAL , PremissionUtils.CAMERA};
 * premissionUtils.askPermission(premission, 100);
 * <p>
 * 取得权限处理
 * @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
 * super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 * premissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
 * }
 * <p>
 * 常见问题
 * 点击不调取
 * 查看manifest里面是否添加了需要申请的权限
 * 高版本也需要添加危险权限
 * fragment里面需要做额外的处理
 * <p>
 * <p>
 * Created by Administrator on 2016/9/5.
 */
public class PermissionUtils {

    private AppCompatActivity rootActivity;
    public static int REQUEST_CODE_PERMISSION = 0x00099;
    public static int REQUEST_CODE = 100;
    public static String CAMERA = Manifest.permission.CAMERA;
    public static String CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static String VOICE = Manifest.permission.READ_VOICEMAIL;
    public static String AUDIO = Manifest.permission.RECORD_AUDIO;
    public static String PHONE_STATUS = Manifest.permission.READ_PHONE_STATE;
    public static String WRITE_EXTERNAL = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static String READ_EXTERNAL = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static String LOCION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static String ACCESS_LOCATION_EXTRA_COMMANDS = Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;
    public static String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static String LOCATION_HARDWARE = Manifest.permission.LOCATION_HARDWARE;
    public static String INSTALL_LOCATION_PROVIDER = Manifest.permission.INSTALL_LOCATION_PROVIDER;
    public static String CONTROL_LOCATION_UPDATES = Manifest.permission.CONTROL_LOCATION_UPDATES;
    private IPermissionFinish iPermissionFinish;

    public PermissionUtils(AppCompatActivity rootActivity) {
        this.rootActivity = rootActivity;
    }

    /**
     * 申请接口
     */
    public void registerPermissionListener(IPermissionFinish iPermissionFinish) {
        this.iPermissionFinish = iPermissionFinish;
    }

    /**
     * 检查是否需要申请权限
     */
    private boolean checkPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT < 22) {
            return true;
        }
        for (String permission : permissions) {
            Logger.i("需要授权权限："
                    + permission
                    + "\n是否授权(0:已授权 -1:未授权)："
                    + ContextCompat.checkSelfPermission(rootActivity, permission)
                    + "\n是否需要授权:"
                    + ActivityCompat.shouldShowRequestPermissionRationale(rootActivity, permission));
            if (ContextCompat.checkSelfPermission(rootActivity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取需要申请的权限
     */
    private List<String> getNeedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(rootActivity, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(rootActivity, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    /**
     * 申请权限
     * 在需要申请权限的地方调用
     */
    public void askActivityPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermission(permissions)) {
            if (iPermissionFinish != null) {
                iPermissionFinish.permissionSuccess();
            }
        } else {
            List<String> needPermissions = getNeedPermissions(permissions);
            ActivityCompat.requestPermissions(rootActivity, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    public void askFragmentPermission(String[] permissions, int requestCode , Fragment fragment) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermission(permissions)) {
            if (iPermissionFinish != null) {
                iPermissionFinish.permissionSuccess();
            }
        } else {
            List<String> needPermissions = getNeedPermissions(permissions);
            fragment.requestPermissions(needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }


    /**
     * 系统权限回调处理
     * 需要在系统onRequestPermissionsResult方法里面调用
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                if (iPermissionFinish != null) {
                    iPermissionFinish.permissionSuccess();
                } else {
                    showTipsDialog();
                }
            } else {
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            Logger.i("确认所有的权限是否都已授权：" + grantResults);
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(rootActivity)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + rootActivity.getPackageName()));
        //这里有的应用汇崩溃 所以使用try-catch方法处理
        try {
            rootActivity.startActivity(intent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 成功失败接口
     */
    public interface IPermissionFinish {
        void permissionSuccess();
    }

    /**
     * 销毁
     */
    public void unregister() {
        if (rootActivity != null) {
            rootActivity = null;
        }
    }

}
