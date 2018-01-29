package com.zeronight.templet.module.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseAdapter;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.data.EventBusBundle;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.utils.DialogUtils;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;
import com.zeronight.templet.module.cart.CartAdapter;
import com.zeronight.templet.module.cart.CartBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * Created by Administrator on 2017/10/9.
 */

public class FourFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private TitleBar titlebar;
    private XRecyclerView xrv;
    private TextView tv_all;
    private SuperTextView stv_confirmorder;
    private TextView tv_price;
    private SuperTextView stv_delete;
    private RelativeLayout rl_bottom;
    //
    private boolean isEdit = false;
    private boolean isAll = false;
    public final static String CHOOSE_ALL = "CHOOSE_ALL";
    public final static String CHOOSE_NOT_ALL = "CHOOSE_NOT_ALL";
    public final static String NUMBER_CHANGE = "NUMBER_CHANGE";
    private CartAdapter cartAdapter;
    private List<CartBean> carts = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        titlebar = (TitleBar) view.findViewById(R.id.titlebar);
        titlebar.setOnTitlebarClickListener(new TitleBar.TitlebarClick() {
            @Override
            public void onRightClick() {
                if (isEdit) {
                    showCartView();
                } else {
                    showEditView();
                }
            }

            @Override
            public void onBackClick() {

            }
        });
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
        initCartList();
        tv_all = (TextView) view.findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        stv_confirmorder = (SuperTextView) view.findViewById(R.id.stv_confirmorder);
        stv_confirmorder.setOnClickListener(this);
        stv_delete = (SuperTextView) view.findViewById(R.id.stv_delete);
        stv_delete.setOnClickListener(this);
        rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_price.setText(getString(R.string.cart_allprice , 0.00f));
    }

    private void initCartList() {
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setLoadingMoreEnabled(false);
        carts.addAll(TestData.getCartLists());
        cartAdapter = new CartAdapter(getActivity(), carts);
        cartAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
            @Override
            public void longClick(final int position) {
                DialogUtils.showDeleteDialog(getActivity(), "该商品", new DialogUtils.OnDialogButtonClickListener() {
                    @Override
                    public void cancel(DialogInterface dialogInterface, int i) {

                    }

                    @Override
                    public void ok(DialogInterface dialogInterface, int i) {
                        ToastUtils.showMessage("删除" + carts.get(position).toString());
                    }
                });
            }
        });
        xrv.setAdapter(cartAdapter);
    }

    private void showEditView() {
        titlebar.setRightText("完成");
        stv_confirmorder.setVisibility(View.GONE);
        stv_delete.setVisibility(View.VISIBLE);
        tv_price.setVisibility(View.GONE);
        isEdit = true;
    }

    private void showCartView() {
        titlebar.setRightText("编辑");
        stv_confirmorder.setVisibility(View.VISIBLE);
        stv_delete.setVisibility(View.GONE);
        tv_price.setVisibility(View.VISIBLE);
        isEdit = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                if (isAll) {
                    cartAdapter.chooseNone();
                    tv_all.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choice_normal , 0 ,0 ,0);
                }else{
                    cartAdapter.chooseAll();
                    tv_all.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choice_select , 0 ,0 ,0);
                }
                isAll = !isAll;
                calculatingPrice();
            break;
            case R.id.stv_confirmorder:
                getCoose();
                break;
            case R.id.stv_delete:
                getCoose();
                break;
        }
    }

    @Subscribe
        public void setAllChoose(EventBusBundle eventBusBundle){
            if (eventBusBundle.getKey().equals(CHOOSE_ALL)) {
                tv_all.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choice_select , 0 ,0 ,0);
                isAll = true;
                calculatingPrice();
            }
        }

    @Subscribe
    public void setNotAllChoose(EventBusBundle eventBusBundle){
        if (eventBusBundle.getKey().equals(CHOOSE_NOT_ALL)) {
            tv_all.setCompoundDrawablesWithIntrinsicBounds(R.drawable.choice_normal , 0 ,0 ,0);
            isAll = false;
            calculatingPrice();
        }
    }

    @Subscribe
    public void numberChange(EventBusBundle eventBusBundle){
        if (eventBusBundle.getKey().equals(NUMBER_CHANGE)) {
            calculatingPrice();
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private List<String> getCoose(){
        List<String> list = new ArrayList<>();
        for (Integer key : cartAdapter.map.keySet()) {
            CartBean cartBean = cartAdapter.map.get(key);
            list.add(cartBean.getId());
        }
        ToastUtils.showMessage(list.toString());
        return list;
    }

    private void calculatingPrice(){
        Float allPrice = 0.00f;
        for (Integer key : cartAdapter.map.keySet()) {
            CartBean cartBean = cartAdapter.map.get(key);
            String num = cartBean.getNum();
            int numInt = 0;
            String price = cartBean.getPrice();
            Float privceFloat = 0.00f;
            if (XStringUtils.isStringAreNum(num)) {
                numInt = Integer.parseInt(num);
            }
            if (XStringUtils.isStringAreNum(price)) {
                privceFloat = Float.parseFloat(price);
            }
            allPrice += numInt * privceFloat;
        }
        tv_price.setText(getString(R.string.cart_allprice , allPrice));
    }



}
