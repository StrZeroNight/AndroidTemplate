package com.zeronight.templet.wxapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zeronight.templet.R;
import com.zeronight.templet.common.application.BaseApplication;
import com.zeronight.templet.common.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/23.
 */

public class WxUtils {

    private static final String TAG = "WxUtils";
    public final static String WXAppId = "wx574d0dc267dfc7a9";
    private final static int WX_CIRCLE_OF_FRENDS = 0;
    private final static int WX_FRENDS = 1;
    private static IWXAPI api = null;
    private static WxUtils wxUtils = null;
    public static String appName = "";

    private WxUtils() {
        api = WXAPIFactory.createWXAPI(BaseApplication.getInstance().getApplicationContext(), WXAppId, true);
        api.registerApp(WXAppId);
    }

    public static WxUtils getInstance() {
        if (wxUtils == null) {
            wxUtils = new WxUtils();
        }
        return wxUtils;
    }

    /**
     * 判断微信有没有被安装
     */
    public boolean isWXAppInstalledAndSupported() {
        boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled()
                && api.isWXAppSupportAPI();
        return sIsWXAppInstalledAndSupported;
    }

    /**
     * 调用微信分享朋友圈
     */
    public void showShareFrends(String url, String appName, String descripe) {
        if (isWXAppInstalledAndSupported()) {
            wxShare(WX_FRENDS, url, appName, descripe);
        } else {
            ToastUtils.showMessage("您还没有安装微信");
        }
    }

    /**
     * 调用微信分享好友
     */
    public void showShareCircleOfFrends(String url, String appName, String descripe) {
        if (isWXAppInstalledAndSupported()) {
            wxShare(WX_CIRCLE_OF_FRENDS, url, appName, descripe);
        } else {
            ToastUtils.showMessage("您还没有安装微信");
        }
    }

    /**
     * 微信分享
     */
    private void wxShare(int flag, String url, String appName, String descripe) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = appName;
        msg.description = descripe;
//      这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(BaseApplication.getInstance().getResources() , R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    /**
     * 解析微信支付数据 content从后台获取
     */
    public void WXPay(String content) throws JSONException {
        Logger.i("==========content微信支付返回的数据:" + content);
        JSONObject json = new JSONObject(content);
        if (null != json) {
            String errCodeDes = json.optString("errCodeDes");
            if (!TextUtils.isEmpty(errCodeDes)) {
                Log.i(TAG, "==========返回错误" + json.getString("errCodeDes"));
            } else {
                if (!json.has("retcode")) {
                    PayReq req = new PayReq();
                    req.appId = json.getString("appId");
                    req.partnerId = json.getString("partnerid");//少返回这个
                    req.prepayId = json.getString("prepay_id");
                    req.nonceStr = json.getString("nonceStr");
                    req.timeStamp = json.getString("timeStamp");
                    req.packageValue = json.getString("package");
                    req.sign = json.getString("signType");
                    req.extData = "app data"; // optional
                    ToastUtils.showMessage("正在调起支付");
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                } else {
                    Log.d(TAG, "==========返回错误" + json.getString("errCodeDes"));
                }
            }
        }

    }

    /**
     * 微信登录
     */
    public void WXLogin() {
        if (isWXAppInstalledAndSupported()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";//固定填写这个
            req.state = "getuserinfo";//自己生成的校验标记
            api.sendReq(req);
        } else {
            ToastUtils.showMessage("您还没有安装微信");
        }
    }

}
