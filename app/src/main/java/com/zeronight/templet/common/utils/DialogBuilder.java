package com.zeronight.templet.common.utils;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by Administrator on 2017/11/25.
 */

public class DialogBuilder {

    AlertDialog.Builder builder;

    public DialogBuilder() {

    }

    public DialogBuilder setContext(AppCompatActivity context){
        builder = new AlertDialog.Builder(context);
        return this;
    }

    public DialogBuilder setTitle(String title){
        builder.setTitle(title);
        return this;
    }

    public DialogBuilder setContent(String content){
        builder.setMessage(content);
        return this;
    }


    public DialogBuilder setHtmlContent(String htmlContent){
        //eg : "忘记密码请联系客服<font color='#3DC8CB'>400-168-5116"
        Spanned spanned = Html.fromHtml(htmlContent);
        builder.setMessage(spanned);
        return this;
    }

    public DialogBuilder setNegativeButton(String btnText){
        builder.setNegativeButton(btnText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        return this;
    }

    public DialogBuilder setNegativeButton(){
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return this;
    }

    public DialogBuilder setPositiveButton(String btnText){
        builder.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        return this;
    }


    public DialogBuilder build(){
        builder.show();
        return this;
    }


//    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//            builder.setTitle("找回密码");
//            Spanned spanned = Html.fromHtml("忘记密码请联系客服<font color='#3DC8CB'>400-168-5116");
//            builder.setMessage(spanned);
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
//            builder.setPositiveButton("联系客服", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                }
//            });
//            builder.show();

}
