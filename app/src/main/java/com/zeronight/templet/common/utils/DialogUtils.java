package com.zeronight.templet.common.utils;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.application.BaseApplication;
import com.zeronight.templet.common.widget.ArrorText;

import java.util.Calendar;


/**
 * 公共的对话框方法
 * <p/>
 * Created by Administrator on 2016/6/2.
 */
public class DialogUtils {

    public static void showPayDialog(final Context context, final OnDialogButtonClickListener onDialogButtonClickListener) {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(context);
        dialog.setTitle("确认暂不支付？");
        dialog.setMessage("您的订单暂未支付，确认离开？");

        dialog.setPositiveButton("确认离开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDialogButtonClickListener.ok(dialogInterface, i);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    /**
     * 更新对话框
     */
    public static void showUpdateDialog(final Context context, String oldVersionCode, String newVersionCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("软件更新");
        builder.setMessage("您目前的版本是" + oldVersionCode + "，检测到有新版本" + newVersionCode + "，请点击立即更新下载最新版本软件");

        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    /**
     * 进度条dialog
     */
    public static void showProgressBarz(Context context) {

        ProgressDialog dialog = new ProgressDialog(context);
        //设置进度条风格，风格为圆形，旋转的
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        //设置ProgressDialog 标题
//        dialog.setTitle("进度对话框");
        //设置ProgressDialog 提示信息
        dialog.setMessage("正在下载请稍后");
//        //设置ProgressDialog 标题图标
//        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        //设置ProgressDialog的最大进度
        dialog.setMax(100);
        //设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(false);
        //显示
        dialog.show();
        //设置ProgressDialog的当前进度
        dialog.setProgress(0);

    }

    public static void showProgressBars() {

        ProgressDialog dialog = new ProgressDialog(BaseApplication.getInstance().getBaseContext());
        //设置进度条风格，风格为圆形，旋转的
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        //设置ProgressDialog 标题
//        dialog.setTitle("进度对话框");
        //设置ProgressDialog 提示信息
        dialog.setMessage("正在下载请稍后");
//        //设置ProgressDialog 标题图标
//        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        //设置ProgressDialog的最大进度
        dialog.setMax(100);
        //设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(false);
        //显示
        dialog.show();
        //设置ProgressDialog的当前进度
        dialog.setProgress(0);

    }

    /**
     * 弹出时间选择器 显示在textview中
     */
    public static void showTimePicker(final TextView tv_show, Context context) {

        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                tv_show.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    /**
     * 弹出时间选择器 返回时间
     */
    public static void showTimePicker(final ArrorText arrorText, Context context) {

        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                //直接联网调用数据
                String birthday = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                UserInfoPresenter.getInstance().changeBirthday(birthday);

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    public static void showDeleteDialog(Context context, String content, final OnDialogButtonClickListener onDialogButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("请问是否要删除" + content);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDialogButtonClickListener.cancel(dialogInterface, i);
            }
        });
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDialogButtonClickListener.ok(dialogInterface, i);
            }
        });
        builder.show();
    }

    public static void showNumChangeDialog(final Context context, final int num, final OnDialogButtonClickListener2 onDialogButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_numchange, null, false);
        final ImageButton ib_minus_cargoods = (ImageButton) view.findViewById(R.id.ib_minus_cargoods);
        final EditText et_num_change = (EditText) view.findViewById(R.id.et_num_change);
        ImageButton ib_plus_cargoods = (ImageButton) view.findViewById(R.id.ib_plus_cargoods);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) view.findViewById(R.id.tv_sure);

        et_num_change.setText(num + "");
        et_num_change.selectAll();

        ib_minus_cargoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = et_num_change.getText().toString();
                if (num.length() < 9) {
                    int numz = Integer.parseInt(num);
                    if (numz > 1) {
                        numz--;
                        et_num_change.setText(numz + "");
                    } else {
                        ToastUtils.showMessage("您输入的数量过大");
                    }
                }
            }
        });

        ib_plus_cargoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = et_num_change.getText().toString();
                if (num.length() < 9) {
                    int numz = Integer.parseInt(num);
                    numz++;
                    et_num_change.setText(numz + "");
                } else {
                    ToastUtils.showMessage("您输入的数量过大");
                }
            }
        });

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sProNum = et_num_change.getText().toString();
                if (sProNum.length() < 9) {
                    if (!XStringUtils.isEmpty(sProNum) && XStringUtils.isStringAreNum(sProNum)) {
                        int proNum = Integer.parseInt(sProNum);
                        if (proNum > 0) {
                            onDialogButtonClickListener.numchange(alertDialog, proNum);
                        } else {
                            ToastUtils.showMessage("数量不能为0");
                        }
                    }else{
                        ToastUtils.showMessage("请输入数量");
                    }
                } else {
                    ToastUtils.showMessage("您输入的数量过大");
                }
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.setView(view);

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_num_change, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                CommonUtils.hideSoft((AppCompatActivity) context, et_num_change);
            }
        });

        alertDialog.setCancelable(false);
        alertDialog.show();

//        Window window = alertDialog.getWindow();
//        window.setContentView(view.build(context,alertDialog));
    }


    public interface OnDialogButtonClickListener {

        void cancel(DialogInterface dialogInterface, int i);

        void ok(DialogInterface dialogInterface, int i);

    }

    public interface OnDialogButtonClickListener2 {

        void numchange(AlertDialog alertDialog, int num);

    }

}
