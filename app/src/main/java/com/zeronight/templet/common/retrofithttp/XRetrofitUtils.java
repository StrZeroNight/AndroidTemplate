package com.zeronight.templet.common.retrofithttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.zeronight.templet.common.application.BaseApplication;
import com.zeronight.templet.common.data.CommonData;
import com.zeronight.templet.common.data.CommonString;
import com.zeronight.templet.common.data.CommonUrl;
import com.zeronight.templet.common.utils.AppSetting;
import com.zeronight.templet.common.utils.ToastUtils;
import com.zeronight.templet.common.utils.XStringUtils;
import com.zeronight.templet.module.login.LoginActivity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 配置TOKEN 参数名
 * <p>
 * 优化问题：
 * 上传图片和上传json或者文件分开处理
 * 日志报出的位置是代码所在位置而不是封装类
 * 日志不需要报出线程
 * 进一步封装不同网络状态的显示情况loadandretry
 * <p>
 * Created by Administrator on 2016/11/4.
 */
public class XRetrofitUtils {

    private Builder builder;
    private final static String TOKEN = "TOKEN";

    public XRetrofitUtils(Builder builder) {
        this.builder = builder;
    }

    private Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(addLogSetting())
                .baseUrl(CommonUrl.BASE_URL)
                .build();
        return retrofit;
    }

    private OkHttpClient addLogSetting() {
        HttpLogInterceptor httpLoggingInterceptor = null;
        if (builder.object != null) {
            httpLoggingInterceptor = new HttpLogInterceptor(java2Map(builder.object).toString());
        }else{
            httpLoggingInterceptor = new HttpLogInterceptor(builder.params.toString());
        }
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    public void AsynGet(@NonNull final OnResultListener onResultListener) {
        //判断网络连接
        if (!isNetworkConnected()) {
            ToastUtils.showMessage("无法连接到网络");
            onResultListener.onNetWorkError();
            return;
        }
        Retrofit retrofit = createRetrofit();
        HttpMethod HttpMethod = retrofit.create(HttpMethod.class);
        Call<ResponseBody> call = HttpMethod.getMethod(builder.url, builder.params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                handleResult(response, onResultListener);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.e(t.toString());
                onResultListener.onServerError();
            }
        });
    }

    public void AsynPost(@NonNull final OnResultListener onResultListener) {
        //判断网络连接
        if (!isNetworkConnected()) {
            ToastUtils.showMessage("无法连接到网络");
            onResultListener.onNetWorkError();
            return;
        }
        Retrofit retrofit = createRetrofit();
        HttpMethod HttpMethod = retrofit.create(HttpMethod.class);
        Call<ResponseBody> call = HttpMethod.postMethod(builder.url, builder.params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                handleResult(response, onResultListener);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.e(t.toString());
                onResultListener.onServerError();
            }
        });

    }

    public void AsynPostByBean(@NonNull final OnResultListener onResultListener) {
        //判断网络连接
        if (!isNetworkConnected()) {
            ToastUtils.showMessage("无法连接到网络");
            onResultListener.onNetWorkError();
            return;
        }

        Retrofit retrofit = createRetrofit();
        HttpMethod HttpMethod = retrofit.create(HttpMethod.class);
        if (builder.object != null) {
            Call<ResponseBody> call = HttpMethod.postMethod(builder.url, java2Map(builder.object));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    handleResult(response, onResultListener);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Logger.e(t.toString());
                    onResultListener.onServerError();
                }
            });
        } else {
            Logger.e("object不能为空");
        }

    }

    private Map java2Map(Object javaBean) {
        String s = JSON.toJSONString(javaBean);
        JSONObject myJson = JSONObject.parseObject(s);
        Map m = myJson;
        //object和 map可以一起使用
        if (builder.params != null) {
            m.putAll(builder.params);
        }
        return m;
    }

    /**
     * 文件或图片上传
     */
    public void update(File file, String key, @NonNull final OnResultListener onResultListener) {
        //判断网络连接
        if (!isNetworkConnected()) {
            ToastUtils.showMessage("无法连接到网络");
            onResultListener.onNetWorkError();
            return;
        }
        Retrofit retrofit = createRetrofit();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(TOKEN, CommonData.getToken())
                .addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .build();
        // 执行请求
        HttpMethod HttpMethod = retrofit.create(HttpMethod.class);
        Call<ResponseBody> call = HttpMethod.uploadFileOrImage(builder.url, requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                handleResult(response, onResultListener);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.e(t.toString());
                onResultListener.onServerError();
            }
        });

    }

    private void handleResult(Response<ResponseBody> response , @NonNull final OnResultListener onResultListener) {
        //判断返回结果
        if (response.code() == 200) {
            try {
                String string = response.body().string();
                if (string.startsWith("{") || string.startsWith("(")) {
                    if (string != null) {
                        ResultBean resultBean = JSON.parseObject(string, ResultBean.class);
                        int returnCode = resultBean.getCode();
                        String message = resultBean.getMsg();
                        String resultData = resultBean.getData();
                        Logger.i("returnCode:" + returnCode);
                        // 返回值处理
                        if (returnCode == 1) {
                            onResultListener.onSuccess(resultData);
                        }else if (returnCode == 0){
                            onResultListener.onNoData();
                        }else if(returnCode == 20003){
                            LoginActivity.start(BaseApplication.getInstance());
                            AppSetting.putBoolean(CommonString.USER_IS_LOGIN , false);
                            onResultListener.onServerError();
                        }else{
                            if (!XStringUtils.isEmpty(message)) {
                                ToastUtils.showMessage(message);
                            }
                            onResultListener.onServerError();
                        }
                    } else {
                        onResultListener.onNoData();
                    }
                }else{
                    Logger.i("服务器返回的不是json格式");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            onResultListener.onServerError();
        }
    }

    /**
     * 网络请求回调 根据后台返回的数据调节对应的方法
     */
    public interface OnResultListener {

        void onNetWorkError();

        void onSuccess(String data);

        void onNoData();

        void onServerError();

    }

    /**
     * 建造者模式 内部类
     */
    public static final class Builder {

        private String url = "";
        private Map<String, String> params = new HashMap<>();
        private Object object = null;

        public Builder() {
            params.put(TOKEN, CommonData.getToken());//所有必传参数都可以写在这里
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setParams(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Builder setObjectParams(Object object) {
            this.object = object;
            return this;
        }

        public XRetrofitUtils build() {
            return new XRetrofitUtils(this);
        }

    }

    /**
     * 判断网络连接
     */
    public boolean isNetworkConnected() {

        if (BaseApplication.getInstance().getApplicationContext() != null) {
            Context context = BaseApplication.getInstance().getApplicationContext();
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}

