package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/1/14 0014.
 */

public class UpdateUtils {
    private Context mContext;
    private String clazz;
    private String packagename,AppName;
    private int VersionCode;

    public UpdateUtils(Context context, String packagename, String AppName, int VersionCode)
    {
        this.mContext = context;
        this.packagename = packagename;
        this.AppName = AppName;
        this.VersionCode = VersionCode;
    }
    public void update(final CallBack callBack) {

        HttpManager.getDataPagingInfo(mContext, packagename, VersionCode, AppName, new HttpRequestHandler<Result>() {
            @Override
            public void onSuccess(Result data) {
                if(data != null){
                    if (data.getCode() == 200){
                        ArrayList<AppResult> appResultlist = JSONObject.parseObject(String.valueOf(data.getObject()), new TypeReference<ArrayList<AppResult>>() {});
                        AppResult appResult = appResultlist.get(0);
                        data.setObject(appResult);
                    }
                    callBack.callback(data);
                }
            }

            @Override
            public void onSuccess(Result data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(mContext,error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
