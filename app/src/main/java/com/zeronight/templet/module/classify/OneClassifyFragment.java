package com.zeronight.templet.module.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zeronight.templet.R;
import com.zeronight.templet.common.base.BaseFragment;
import com.zeronight.templet.common.data.TestData;
import com.zeronight.templet.common.widget.FilterBar;
import com.zeronight.templet.common.widget.SearchBarClick;
import com.zeronight.templet.common.widget.SuperTextView;
import com.zeronight.templet.common.widget.TitleBar;

import java.util.List;

import static com.zeronight.templet.module.classify.BrandAdapter.brandMap;


/**
 * Created by Administrator on 2017/10/9.
 */
public class OneClassifyFragment extends BaseFragment implements View.OnClickListener {

    protected SearchBarClick searchbar;
    protected TabLayout tablayout;
    private FilterBar filterbar;
    private DrawerLayout drawerlayout;
    private XRecyclerView xrv;
    protected TitleBar titlebar;
    private TextView tv_brand;
    private EditText et_lowestprice_filter;
    private EditText et_highestprice_filter;
    private RecyclerView recyclerview_filter;
    private SuperTextView stv_clear;
    private LinearLayout ll_bottom;
    private RelativeLayout rl_fliter1;
    private RelativeLayout rl_fliter2;
    private RelativeLayout rl_x2;
    private TitleBar titlebar2;
    private RecyclerView rv_brand;
    private SuperTextView stv_clear2;
    private SuperTextView stv_ok2;
    private LinearLayout ll_bottom2;
    private RelativeLayout rl_x;
    private BrandAdapter brandAdapter;
    private SuperTextView stv_ok;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oneclassify, container, false);
        initView(view);
        initData();
        return view;
    }

    protected void initData() {
        //searchresultfragmetn need
    }

    private void initView(View view) {
        drawerlayout = (DrawerLayout) view.findViewById(R.id.drawerlayout);
        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        searchbar = (SearchBarClick) view.findViewById(R.id.searchbar);
        initSeatchbar();
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        iniTablayout(TestData.getLists());
        filterbar = (FilterBar) view.findViewById(R.id.filterbar);
        initFilterBar();
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
        xrv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        xrv.setAdapter(new SmallProListAdapter(getActivity(), TestData.getLists()));
        titlebar = (TitleBar) view.findViewById(R.id.titlebar);
        tv_brand = (TextView) view.findViewById(R.id.tv_brand);
        et_lowestprice_filter = (EditText) view.findViewById(R.id.et_lowestprice_filter);
        et_highestprice_filter = (EditText) view.findViewById(R.id.et_highestprice_filter);
        recyclerview_filter = (RecyclerView) view.findViewById(R.id.recyclerview_filter);
        stv_clear =  view.findViewById(R.id.stv_clear);
        stv_ok =  view.findViewById(R.id.stv_ok);
        ll_bottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        rl_x2 =  view.findViewById(R.id.rl_x2);
        rl_x2.setOnClickListener(this);
        rl_fliter1 = (RelativeLayout) view.findViewById(R.id.rl_fliter1);
        rl_fliter1.setOnClickListener(this);
        titlebar2 = (TitleBar) view.findViewById(R.id.titlebar2);
        rl_fliter2 = view.findViewById(R.id.rl_fliter2);
        initTitlebar();
        initBrandRv(view);
        stv_clear2 = (SuperTextView) view.findViewById(R.id.stv_clear2);
        stv_ok2 = (SuperTextView) view.findViewById(R.id.stv_ok2);
        ll_bottom2 = (LinearLayout) view.findViewById(R.id.ll_bottom2);
        rl_x = (RelativeLayout) view.findViewById(R.id.rl_x);
        rl_x.setOnClickListener(this);
        stv_clear2.setOnClickListener(this);
        stv_ok2.setOnClickListener(this);
        rl_fliter2 = (RelativeLayout) view.findViewById(R.id.rl_fliter2);
        stv_clear = (SuperTextView) view.findViewById(R.id.stv_clear);
        stv_clear.setOnClickListener(this);
        stv_ok = (SuperTextView) view.findViewById(R.id.stv_ok);
        rv_brand = (RecyclerView) view.findViewById(R.id.rv_brand);
        stv_ok.setOnClickListener(this);
        brandMap.clear();
    }

    private void initBrandRv(View view) {
        rv_brand = (RecyclerView) view.findViewById(R.id.rv_brand);
        rv_brand.setLayoutManager(new LinearLayoutManager(getActivity()));
        brandAdapter = new BrandAdapter(getActivity(), TestData.getLists());
        rv_brand.setAdapter(brandAdapter);
    }

    private void initTitlebar() {
        titlebar.setOnTitlebarClickListener(new TitleBar.TitlebarClick() {
            @Override
            public void onRightClick() {

            }

            @Override
            public void onBackClick() {
                drawerlayout.closeDrawer(Gravity.RIGHT);
            }
        });
        titlebar2.setOnTitlebarClickListener(new TitleBar.TitlebarClick() {
            @Override
            public void onRightClick() {

            }

            @Override
            public void onBackClick() {
                showFilter1();
            }
        });
    }

    private void initFilterBar() {
        filterbar.setOnFilterbarClickListener(new FilterBar.FilterbarClick() {
            @Override
            public void onFirstClick() {

            }

            @Override
            public void onSecondClick() {

            }

            @Override
            public void onThirdClick() {

            }
        });
    }

    protected void iniTablayout(final List<String> list) {
        if (list.size() < 4) {
            tablayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        //动态添加数据
        for (int i = 0; i < list.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(list.get(i).toString()));
        }
        //监听viewpager滑动
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
//                    for (int i = 0; i < list.size(); i++) {
//                        if (position == i) {
//                            page = 1;
//                            currentTab = position;
//    //                        tabId = tabNames.get(i).toString();
//                        }
//                    }
//                    getListInfo(xrecyclerview_basetablist, ll_root_wrongdata, ll_root_nodata);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void initSeatchbar() {
        searchbar.setOnClickSearchClickListener(new SearchBarClick.OnClickSearchClickListener() {
            @Override
            public void OnSearchClicke() {
                clickSearchBar();
            }

            @Override
            public void OnLeftClick() {

            }

            @Override
            public void OnRightClick() {
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    protected void clickSearchBar() {

    }

    protected void clickBack() {
        //searchresultfragmetn need
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_x2:
                showFilter2();
                break;
            case R.id.rl_fliter1:
                break;
            case R.id.rl_x:
                break;
            case R.id.stv_clear2:
                brandMap.clear();
                brandAdapter.notifyDataSetChanged();
                break;
            case R.id.stv_ok2:
                showFilter1();
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer key : brandMap.keySet()) {
                    BrandBean brandBean = brandMap.get(key);
                    stringBuilder.append(brandBean.getBrandString() + " ");
                }
                tv_brand.setText(stringBuilder);
                break;
            case R.id.stv_clear:
                tv_brand.setText("");
                et_highestprice_filter.setText("");
                et_lowestprice_filter.setText("");
                break;
            case R.id.stv_ok:
                drawerlayout.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    private void showFilter2() {
        rl_fliter1.setVisibility(View.GONE);
        rl_fliter2.setVisibility(View.VISIBLE);
    }

    private void showFilter1() {
        rl_fliter1.setVisibility(View.VISIBLE);
        rl_fliter2.setVisibility(View.GONE);
    }


}
