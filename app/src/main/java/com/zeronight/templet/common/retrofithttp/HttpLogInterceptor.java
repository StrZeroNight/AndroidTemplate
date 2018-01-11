package com.zeronight.templet.common.retrofithttp;

import com.orhanobut.logger.Logger;
import com.zeronight.templet.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Administrator on 2017/3/18.
 */

public class HttpLogInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int JSON_INDENT = 2;
    private String params;

    public HttpLogInterceptor(String params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        if (BuildConfig.DEBUG) {
            showObjectJson( responseBody , request , response);
        }
        return response;
    }

    private void showObjectJson(ResponseBody responseBody , Request request , Response response) throws IOException {
        String url = "Url:" + request.method() + ' ' + request.url() + "\n";
        String code = "Code:" + response.code() + "\n";
        String content = FormattingJson(getResponseContnet(responseBody)) + "\n";
        String paramz = "Params:" + params + "\n";
        Logger.d(url + code + paramz + content);
    }

    private String getResponseContnet(ResponseBody responseBody) throws IOException {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        MediaType contentType = responseBody.contentType();
        if (contentType == null) {
            return "";
        }
        Charset charset = contentType.charset(UTF8);
        String data = buffer.clone().readString(charset);
        return data;
    }

    private String FormattingJson(String json) {
        if (json == null || json.length() == 0) {
            return "Empty/Null json content";
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                return message;
            }
            return "Invalid Json" + "\n" + json;
        } catch (JSONException e) {
            return "Invalid Json" + "\n" + json;
        }
    }

}
