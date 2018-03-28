package com.zeronight.templet.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.widget.SuperTextView;

/**
 * Created by Administrator on 2017/10/19.
 */
public class TipDialog extends Dialog {

    private AlertDialog dialog;

    public TipDialog(Context context, String tip) {
        super(context);
        showdialog(context, tip , null);
    }

    public TipDialog(Context context, String tip , DialogButtonClick dialogButtonClick) {
        super(context);
        showdialog(context, tip , dialogButtonClick);
    }

    public void showdialog(Context context, final String tip , final DialogButtonClick dialogButtonClick) {

        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_tip);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        TextView tv_tip_content = (TextView) window.findViewById(R.id.tv_tip);
        SuperTextView tv_ok = window.findViewById(R.id.tv_ok);
        SuperTextView tv_no = window.findViewById(R.id.tv_no);
        tv_tip_content.setText(tip);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogButtonClick != null) {
                    dialogButtonClick.onSure();
                }
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dialogButtonClick != null) {
                    dialogButtonClick.onDismiss();
                }
            }
        });

    }

    public interface DialogButtonClick{
        void onDismiss();
        void onSure();
    }

}
