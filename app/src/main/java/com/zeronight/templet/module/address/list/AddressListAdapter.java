package com.zeronight.templet.module.address.list;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.module.address.edit.AddressDetialBean;
import com.zeronight.templet.module.address.edit.AddressEditActivity;

import java.text.ParseException;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AddressListAdapter extends BaseAdapter<AddressDetialBean> {

    //订单结算 或者 我的
    // 1是从购物车跳入 2是从地址编辑跳入
    private int intentType;
    private final static String ADDRESS_TAG_COMPANY = "1";
    private final static String ADDRESS_TAG_HOME = "2";
    private final static String ADDRESS_INFO = "ADDRESS_INFO";

    public AddressListAdapter(Context mContext, List<AddressDetialBean> mList, int intentType) {
        super(mContext, mList);
        this.intentType = intentType;
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {

        AddressViewHolder holderz = (AddressViewHolder) holder;

        //地址信息
        final AddressDetialBean address = mList.get(position);
        final String name = address.getUser();
        String telphone = address.getPhone();
        final String addressId = address.getAddressId();
        String city = address.getCity();

        //显示信息
//        holderz.tv_name_address.setText(name);
//        holderz.tv_phone_address.setText(XStringUtils.hidePhoneMiddle(telphone));
//        holderz.tv_address_address.setText(city + address);

        //显示是否默认
//        if (addressId.equals("1")) {
//            holderz.tv_default_address.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cart_choose_ok, 0, 0, 0);
//        } else {
//            holderz.tv_default_address.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cart_choose_no, 0, 0, 0);
//        }

        //点击选为默认地址
        holderz.tv_default_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentType == AddressListActivity.FROM_CART) {
                    chooseDefaultAddressFromCart(addressId + "");
                } else {
                    chooseDefaultAddress(addressId + "");
                }
            }
        });

        //点击跳转编辑
        holderz.tv_edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressEditActivity.start(mContext, address.getAddressId() + "");
            }
        });

        //删除地址
        holderz.tv_delete_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("是否删除该地址?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteAddress(addressId + "");
                    }
                });
                builder.show();
            }
        });

        //点击选中为需要的地址
        holderz.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentType == AddressListActivity.FROM_CART) {
                    Bundle bundle = new Bundle();
                    // TODO: 2018/1/3 等bean出现后再增加
//                    bundle.putParcelable(ADDRESS_INFO , mList.get(position));
                    EventBus.getDefault().post(new EventBusBundle(ADDRESS_INFO, bundle));
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    activity.finish();
                }
            }
        });

    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mLayoutInflater.inflate(R.layout.item_address, parent, false));
    }

    public class AddressViewHolder extends BaseRecyclerViewHolder {

        private LinearLayout ll_root;
        private TextView tv_tag_address;
        private TextView tv_name_address;
        private TextView tv_phone_address;
        private TextView tv_address_address;
        private LinearLayout ll_defaultaddress_address;
        private TextView tv_default_address;
        private TextView tv_delete_address;
        private TextView tv_edit_address;

        public AddressViewHolder(View View) {
            super(View);
            ll_root = (LinearLayout) View.findViewById(R.id.ll_root);
            tv_tag_address = (TextView) View.findViewById(R.id.tv_tag_address);
            tv_name_address = (TextView) View.findViewById(R.id.tv_name_address);
            tv_phone_address = (TextView) View.findViewById(R.id.tv_phone_address);
            tv_address_address = (TextView) View.findViewById(R.id.tv_address_address);
            ll_defaultaddress_address = (LinearLayout) View.findViewById(R.id.ll_defaultaddress_address);
            tv_default_address = (TextView) View.findViewById(R.id.tv_default_address);
            tv_delete_address = (TextView) View.findViewById(R.id.tv_delete_address);
            tv_edit_address = (TextView) View.findViewById(R.id.tv_edit_address);
        }
    }

    /**
     * 删除地址
     */
    private void deleteAddress(final String customerAcceptAddressId) {
//        customerAcceptAddressId 101 收货地址 id string 必填
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .setParams("addressId", customerAcceptAddressId)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
                ToastUtils.showMessage("删除成功");
                notifyAddressLsit();
//                EventBus.getDefault().post(new EventBusBundle(AddressListActivity.CHECK_ADDRESS, customerAcceptAddressId + ""));
            }

            @Override
            public void onNoData() {

            }

            @Override
            public void onServerError() {

            }
        });
    }


    /**
     * 选中为默认地址
     */
    public void chooseDefaultAddress(String customerAcceptAddressId) {
//        token	是	string
//        aid	是	int	地址id
        final BaseActivity baseActivity = (BaseActivity) this.mContext;
        baseActivity.showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                baseActivity.dismissProgressDialog();
            }

            @Override
            public void onSuccess(String data) {
                baseActivity.dismissProgressDialog();
                ToastUtils.showMessage("设置成功");
                notifyAddressLsit();
            }

            @Override
            public void onNoData() {
                baseActivity.dismissProgressDialog();
            }

            @Override
            public void onServerError() {
                baseActivity.dismissProgressDialog();
            }
        });
    }

    /**
     * 选中为默认地址
     */
    public void chooseDefaultAddressFromCart(String customerAcceptAddressId) {
//        token	是	string
//        aid	是	int	地址id
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
                .setUrl(CommonUrl.login)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {

            }

            @Override
            public void onSuccess(String data) {
//                EventBus.getDefault().post(new EventBusBundle(ConfirmOrderActivity.NOTIFY_DEFAULT_ADDRESS , ""));
                AppCompatActivity activity = (AppCompatActivity) mContext;
                activity.finish();
            }

            @Override
            public void onNoData() {

            }

            @Override
            public void onServerError() {

            }
        });
    }

    private void notifyAddressLsit() {
        EventBus.getDefault().post(new EventBusBundle(AddressListActivity.NOTIFY_ADDRESS, ""));
    }


}
