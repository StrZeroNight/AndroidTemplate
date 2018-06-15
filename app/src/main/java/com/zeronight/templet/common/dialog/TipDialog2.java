package com.zeronight.templet.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeronight.templet.R;


/**
 * Created by Administrator on 2017/10/19.
 */

public class TipDialog2 extends Dialog {

    private AlertDialog dialog;

    public TipDialog2(Context context, Spanned tip) {
        super(context);
        showdialog(context, tip , null);
    }

    public TipDialog2(Context context, Spanned tip , DialogButtonClick dialogButtonClick) {
        super(context);
        showdialog(context, tip , dialogButtonClick);
    }

    public void showdialog(Context context, final Spanned tip , final DialogButtonClick dialogButtonClick) {

        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_tip2);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        final TextView tv_ok = (TextView) window.findViewById(R.id.tv_ok);
        TextView tv_tip = (TextView) window.findViewById(R.id.tv_tip);
        tv_tip.setText(tip);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogButtonClick != null) {
                    dialogButtonClick.onSure();
                }
                dialog.dismiss();
            }
        });

    }

    public interface DialogButtonClick{
        void onSure();
    }

}
