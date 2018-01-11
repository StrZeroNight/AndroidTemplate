package com.zeronight.templet.module.address.chooser;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.zeronight.templet.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddressChooer {

    private AppCompatActivity appCompatActivity;

    public AddressChooer(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        initAddressData();
    }

    private ArrayList<AreaBean> provinceItems = new ArrayList<>();
    private ArrayList<ArrayList<AreaBean.CitiesBean>> cityItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AreaBean.CitiesBean.CountiesBean>>> areaItems = new ArrayList<>();

    public void showAddressChoose(final OnAddressChoose onAddressChoose) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(appCompatActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                AreaBean areaBean = provinceItems.get(options1);
                AreaBean.CitiesBean cityBean = cityItems.get(options1).get(option2);
                AreaBean.CitiesBean.CountiesBean areaCountyBean = areaItems.get(options1).get(option2).get(options3);

                String address = areaBean.getPickerViewText()
                        + "" + cityBean.getPickerViewText()
                        + "" + areaCountyBean.getPickerViewText();
                if (onAddressChoose != null) {
                    onAddressChoose.onAddressChoose(address);
                }
            }
        })
//                .setSubmitText("确定")//确定按钮文字
//                .setCancelText("取消")//取消按钮文字
//                .setTitleText(getString(R.string.current_distribution_area))//标题
//                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(14)//标题文字大小
                .setTitleColor(appCompatActivity.getResources().getColor(R.color.colorPrimary))//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                .setContentTextSize(18)//滚轮文字大小
//                .setLinkage(true)//设置是否联动，默认true
//                .setLabels("省", "市", "区")//设置选择的三级单位
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setCyclic(false, false, false)//循环与否
//                .setSelectOptions(1, 1, 1)  //设置默认选中项
//                .setOutSideCancelable(false)//点击外部dismiss default true
//                .isDialog(true)//是否显示为对话框样式
                .setDividerColor(appCompatActivity.getResources().getColor(R.color.color_white))
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)
                .build();

        optionsPickerView.setPicker(provinceItems, cityItems, areaItems);//添加数据源
        optionsPickerView.show(true);

    }

    private void initAddressData() {
        ArrayList<AreaBean> jsonBean = parseData();
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        provinceItems = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<AreaBean.CitiesBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<AreaBean.CitiesBean.CountiesBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCities().size(); c++) {//遍历该省份的所有城市
                AreaBean.CitiesBean cityBean = jsonBean.get(i).getCities().get(c);
                CityList.add(cityBean);//添加城市

                ArrayList<AreaBean.CitiesBean.CountiesBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCities().get(c).getCounties() == null
                        || jsonBean.get(i).getCities().get(c).getCounties().size() == 0) {
                    City_AreaList.add(new AreaBean.CitiesBean.CountiesBean("0", ""));
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCities().get(c).getCounties().size(); d++) {//该城市对应地区所有数据
                        AreaBean.CitiesBean.CountiesBean areaCountyBean = jsonBean.get(i).getCities().get(c).getCounties().get(d);
                        City_AreaList.add(areaCountyBean);//添加该城市所有地区数据
                    }

                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            cityItems.add(CityList);
            areaItems.add(Province_AreaList);
        }

    }

    public ArrayList<AreaBean> parseData() {//Gson 解析
        String json = getJson(appCompatActivity, "city.json");
        ArrayList<AreaBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(json);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                AreaBean entity = gson.fromJson(data.optJSONObject(i).toString(), AreaBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    public static String getJson(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

    public interface OnAddressChoose {
        void onAddressChoose(String address);
    }

}
