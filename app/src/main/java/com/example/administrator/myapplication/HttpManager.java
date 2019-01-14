package com.example.administrator.myapplication;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2019/1/11 0011.
 */

public class HttpManager {
    /**
     * 解析返回的结果--分页
     */
    public static void getDataPagingInfo(final Context cxt, String packagename,int VersionCode,String AppName, final HttpRequestHandler<Result> handler) {

        String ip_adress = "http://192.168.0.128:8086/update/updateapk";
        Log.i(TAG, "ip_adress=" + ip_adress);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("packageinfo", packagename);
        params.put("appname", AppName);
        params.put("serverVersion", String.valueOf(VersionCode));
        client.post(ip_adress, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, "获取信息失败");
            }
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Result result = JSONObject.parseObject(responseString, new TypeReference<Result>() {});
                if(null == result) {
                    SafeHandler.onFailure(handler, responseString);
                }else {
                    SafeHandler.onSuccess(handler, result);
                }
            }

        });
    }

}
