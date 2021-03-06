package com.zeronight.templet.common.retrofithttp;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface HttpMethod {

    //post方法必须添加FormUrlEncoded post后面的路径不要以/开头 field是传入的参数
    //@Headers({"Content-Type: application/json","Accept: application/json"})//不要加，加了后台收不到参数
    @FormUrlEncoded
    @POST
    Call<ResponseBody> postMethod(@Url String urlPath, @FieldMap Map<String, String> param);

    @GET("{urlPath}")
    Call<ResponseBody> getMethod(@Url String urlPath, @QueryMap Map<String, String> params);

    //文件上传
    @POST
    Call<ResponseBody> uploadFileOrImage(@Url String urlPath, @Body RequestBody Body);

}
