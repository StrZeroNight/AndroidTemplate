package com.zeronight.templet.module.cart;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseActivity;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseRecyclerViewHolder;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.retrofithttp.XRetrofitUtils;
import com.zeronight.templet.common.utils.DialogUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.NumButtom;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

import static com.zeronight.templet.module.cart.CartFragment.CHOOSE_ALL;
import static com.zeronight.templet.module.cart.CartFragment.CHOOSE_NOT_ALL;
import static com.zeronight.templet.module.cart.CartFragment.NUMBER_CHANGE;


/**
 * Created by Administrator on 2018/1/26.
 */
public class CartAdapter extends BaseAdapter<CartBean> {

    private OnButtonClickListenr onButtonClickListenr;
    public Map<Integer , CartBean> map = new HashMap<>();
    BaseActivity baseActivity = (BaseActivity)mContext;

    public CartAdapter(Context mContext, List<CartBean> mList) {
        super(mContext, mList);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) throws ParseException {
        final BaseViewHolder mHolder = (BaseViewHolder) holder;
        final CartBean cartBean = mList.get(position);
        String id = cartBean.getId();
        String price = cartBean.getPrice();
        final String num = cartBean.getNum();
        if (XStringUtils.isStringAreNum(price)) {
            mHolder.tv_price.setText(mContext.getString(R.string.cart_price , Float.parseFloat(price)));
        }
        mHolder.numbutton.setNum(num);
        //判断是否选中
        if (map.containsKey(position)) {
            mHolder.iv_choose.setImageResource(R.drawable.cart_choose_ok);
        }else{
            mHolder.iv_choose.setImageResource(R.drawable.cart_choose_no);
        }

        mHolder.ll_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map.containsKey(position)) {
                    map.remove(position);
                }else{
                    map.put(position , cartBean);
                }
                //判断是否全选
                if (map.size() == mList.size()) {
                    EventBus.getDefault().post(new EventBusBundle(CHOOSE_ALL , ""));
                }else{
                    EventBus.getDefault().post(new EventBusBundle(CHOOSE_NOT_ALL , ""));
                }
                notifyDataSetChanged();
            }
        });

        mHolder.numbutton.setNumChangeListener(new NumButtom.onNumChangeListener() {
            @Override
            public void add(String num) {
//                changeNum(num , cartBean,mHolder.numbutton);
                // TODO: 2018/1/29 使用上面那句
                cartBean.setNum(num);
                EventBus.getDefault().post(new EventBusBundle(NUMBER_CHANGE , ""));
            }

            @Override
            public void mine(String num) {
//                changeNum(num, cartBean,mHolder.numbutton);
                // TODO: 2018/1/29 使用上面那句
                cartBean.setNum(num);
                EventBus.getDefault().post(new EventBusBundle(NUMBER_CHANGE , ""));
            }
        });

        mHolder.numbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showNumChangeDialog(mContext, Integer.parseInt(num) , new DialogUtils.OnDialogButtonClickListener2() {
                    @Override
                    public void numchange(AlertDialog alertDialog, int num) {
                        cartBean.setNum(num + "");
                        mHolder.numbutton.setNum(mList.get(position).getNum() + "");
                        EventBus.getDefault().post(new EventBusBundle(NUMBER_CHANGE , ""));
                        alertDialog.dismiss();
                    }

                });
            }
        });
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_cart, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new BaseViewHolder(view);
    }

    public class BaseViewHolder extends BaseRecyclerViewHolder {
        private LinearLayout ll_choose;
        private ImageView iv_choose;
        private ImageView iv_pic;
        private NumButtom numbutton;
        private TextView tv_price;
        public BaseViewHolder(View itemView) {
            super(itemView);
            numbutton = (NumButtom) itemView.findViewById(R.id.numbutton);
            ll_choose = (LinearLayout) itemView.findViewById(R.id.ll_choose);
            iv_choose = (ImageView) itemView.findViewById(R.id.iv_choose);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

    public interface OnButtonClickListenr {
        void OnButtonClick(int position);
    }

    public void setOnButtonClickListenr(OnButtonClickListenr onButtonClickListenr) {
        this.onButtonClickListenr = onButtonClickListenr;
    }

    public void chooseAll(){
        for (int i = 0; i < mList.size(); i++) {
            map.put(i , mList.get(i));
        }
        notifyDataSetChanged();
    }

    public void chooseNone(){
        for (int i = 0; i < mList.size(); i++) {
            map.remove(i);
        }
        notifyDataSetChanged();
    }

    private void changeNum(final String num , final CartBean cartBean , final NumButtom numButtom) {
        baseActivity.showprogressDialog();
        XRetrofitUtils retrofitUtils = new XRetrofitUtils.Builder()
//                .setUrl(ConstantUrl.login)
//                .setParams("phone", phone)
                .build();
        retrofitUtils.AsynPost(new XRetrofitUtils.OnResultListener() {
            @Override
            public void onNetWorkError() {
                baseActivity.dismissProgressDialog();
                numButtom.minus();
            }

            @Override
            public void onSuccess(String data) {
                baseActivity.dismissProgressDialog();
                ToastUtils.showMessage("修改成功");
                cartBean.setNum(num);
                EventBus.getDefault().post(new EventBusBundle(NUMBER_CHANGE , ""));
            }

            @Override
            public void onNoData() {
                baseActivity.dismissProgressDialog();
                numButtom.minus();
            }

            @Override
            public void onServerError() {
                baseActivity.dismissProgressDialog();
                numButtom.minus();
            }
        });
    }



}
