package com.zeronight.templet.module.filereader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.TbsReaderView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.utils.PermissionUtils;
import com.zeronight.templet.common.utils.ToastUtils;

/**
 * 有可能点击无效 是因为tbs服务jni还没有完全加载出来
 * Created by Administrator on 2018/1/18.
 */

public class FileReadActivity extends BaseActivity implements View.OnClickListener {

    private final static int REQUEST_CODE = 1001;
    private final static int RESULT_CODE = 1002;
    private final static String ID = "ID";
    private WebView tbs_webview;
    private RelativeLayout root;
    private TbsReaderView mTbsReaderView;
    private Button btn_docx;
    private Button btn_xslx;
    private Button btn_pdf;
    private Button btn_png;
    private Button btn_jpeg;

    public static void start(Context context, String id) {
        Intent it = new Intent(context, FileReadActivity.class);
        it.putExtra(ID, id);
        context.startActivity(it);
    }

    public static void start(Context context) {
        Intent it = new Intent(context, FileReadActivity.class);
        context.startActivity(it);
    }


    public static void startActivityForResult(Context context) {
        Intent it = new Intent(context, FileReadActivity.class);
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
        setContentView(R.layout.activity_fileread);
        initView();
    }


    PermissionUtils permissionUtils = new PermissionUtils(FileReadActivity.this);

    private void initView() {

        btn_docx = (Button) findViewById(R.id.btn_docx);
        btn_docx.setOnClickListener(this);
        btn_xslx = (Button) findViewById(R.id.btn_xslx);
        btn_xslx.setOnClickListener(this);
        btn_pdf = (Button) findViewById(R.id.btn_pdf);
        btn_pdf.setOnClickListener(this);
        btn_png = (Button) findViewById(R.id.btn_text);
        btn_png.setOnClickListener(this);
        btn_jpeg = (Button) findViewById(R.id.btn_jpeg);
        btn_jpeg.setOnClickListener(this);
        root = (RelativeLayout) findViewById(R.id.root);
        mTbsReaderView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        root.addView(mTbsReaderView,
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        permissionUtils.registerPermissionListener(new PermissionUtils.IPermissionFinish() {
            @Override
            public void permissionSuccess() {

            }
        });
        String[] premission = {PermissionUtils.PHONE_STATUS, PermissionUtils.WRITE_EXTERNAL};
        permissionUtils.askActivityPermission(premission, PermissionUtils.REQUEST_CODE);

    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        mTbsReaderView.onStop();
        super.onDestroy();
    }

    private void loadFile(String fileName) {
        Bundle bundle = new Bundle();
        String fileNamez = "/" + fileName;
        bundle.putString("filePath", Environment.getExternalStorageDirectory().getPath() + fileNamez);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(fileName), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_docx:
                loadFile("text.docx");
                break;
            case R.id.btn_xslx:
                loadFile("text.xlsx");
                break;
            case R.id.btn_pdf:
                loadFile("text.pdf");
                break;
            case R.id.btn_text:
                loadFile("text.txt");
                break;
            case R.id.btn_jpeg:
                loadFile("text.jpg");
                break;
        }
    }
}
