package com.zeronight.templet.module.equipment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.ToastUtils;

import cgrass.print.printprovider.attr.ESC_SYTLE;
import cgrass.print.printprovider.attr.PrintFormat;
import cgrass.print.printprovider.inter.PrintProviderInterface;
import cgrass.print.printprovider.printer.PrinterProvider;

/**
 * Created by Administrator on 2018/1/12.
 */

public class EquipmentActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    /**
     * 检索wifi设备
     */
    private Button btn_wifi_search;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, EquipmentActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, EquipmentActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, EquipmentActivity.class);
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivityForResult(it, REQUEST_CODE);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent.getStringExtra(ID) != null) {
            String id = intent.getStringExtra(ID);
            ToastUtils.showMessage("获取id" + id);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        initView();
    }

    private void initView() {
        btn_wifi_search = (Button) findViewById(R.id.btn_wifi_search);
        btn_wifi_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wifi_search:
                InitPrint(ESC_SYTLE.MODE_PRINT.WIFI_PRINT, "121.10.40.146","80");
                break;
        }
    }

    PrinterProvider print;
    PrintProviderInterface printerdevice;
    PrintFormat printFormat;
    // 初始化 设置 ip，端口，wifi，打印。。socket通讯
    public void InitPrint(ESC_SYTLE.MODE_PRINT mode, String ip, String port) {
        print = null;
        print = new PrinterProvider(this);
        print.setPrinterConfig(ip, port, "GBK");
        printerdevice = print.CreatePrint(mode);
        printerdevice.InitPrint();
        printerdevice.preparePrint();
        printFormat = new PrintFormat();
        int status = printerdevice.getStatus();

        Logger.i("status:" + status);
    }




}
