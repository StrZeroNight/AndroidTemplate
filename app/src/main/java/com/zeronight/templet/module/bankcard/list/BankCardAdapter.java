package com.zeronight.templet.module.bankcard.list;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.utils.ImageLoad;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.module.bankcard.edit.BankCardEditActivity;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
public class BankCardAdapter extends BaseAdapter<BankCardListBean> {

    public BankCardAdapter(Context mContext, List<BankCardListBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        BaseViewHolder mHolder = (BaseViewHolder) holder;

        final BankCardListBean bankCardBean = mList.get(position);
        String cardholder = bankCardBean.getCardholder();
        String number = bankCardBean.getNumber();
        String open_bank = bankCardBean.getOpen_bank();
        String image = bankCardBean.getImage();
        final String id = bankCardBean.getId();

        mHolder.tv_bank_name.setText(open_bank);
        mHolder.tv_bank_number.setText("尾号：" + XStringUtils.endFourNum(number));
        mHolder.tv_bank_user.setText("持卡人：" + cardholder);
        ImageLoad.loadCircleImage(image , mHolder.iv_bank_image);

        mHolder.stv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("是否删除该银行卡");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                deleteBankCard(id, position);
                            }
                        });
                        builder.show();

            }
        });

        mHolder.stv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankCardEditActivity.start(mContext , "1");
            }
        });

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_bankcard, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        ImageView iv_bank_image;
        RelativeLayout rl_root;
        TextView tv_bank_name;
        TextView tv_bank_number;
        TextView tv_bank_user;
        SuperTextView stv_delete;
        SuperTextView stv_edit;
        public BaseViewHolder(View itemView) {
            super(itemView);
            this.iv_bank_image = (ImageView) itemView.findViewById(R.id.iv_bank_image);
            this.tv_bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
            this.tv_bank_number = (TextView) itemView.findViewById(R.id.tv_bank_number);
            this.tv_bank_user = (TextView) itemView.findViewById(R.id.tv_bank_user);
            this.stv_delete = (SuperTextView) itemView.findViewById(R.id.stv_delete);
            this.stv_edit = (SuperTextView) itemView.findViewById(R.id.stv_edit);
            this.rl_root = (RelativeLayout) itemView.findViewById(R.id.rl_root);
        }
    }

//    private void deleteBankCard(final String id , final int position) {
//        final BaseActivity activity = (BaseActivity) this.mContext;
//        activity.showprogressDialogCanNotCancel();
//        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
//                .setUrl(CommonUrl.login)
//                .setParams("id", id)
//                .build();
//        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
//            @Override
//            public void onNetWorkError() {
//                activity.dismissProgressDialog();
//            }
//
//            @Override
//            public void onSuccess(String data) {
//                activity.dismissProgressDialog();
//            }
//
//            @Override
//            public void onNoData() {
//                activity.dismissProgressDialog();
//                if (id.equals(bankCardId)) {
//                    Logger.i("id:" + id + "   bankCardId:" + bankCardId);
//                    //首先是要刷新本列表 然后刷新余额页面的银行卡信息
//                    EventBus.getDefault().post(new EventBusBundle(BanlaceActivity.NOTIFY_BALANCE , ""));
//                }
//                EventBus.getDefault().post(new EventBusBundle(BankCardListActivity.NOTFY_BANK , ""));
//            }
//
//            @Override
//            public void onServerError() {
//                activity.dismissProgressDialog();
//            }
//        });
//    }

}
