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

    public UpdateUtils(Context context, String packagename, String AppName, int VersionCode, String clazz)
    {
        this.mContext = context;
        this.packagename = packagename;
        this.AppName = AppName;
        this.VersionCode = VersionCode;
        this.clazz = clazz;
    }
    public void update() {
//        packagename = PackageUtils.getPackageName(mContext);
//        VersionCode = PackageUtils.getVersionCode(mContext);
//        AppName = PackageUtils.getAppName(mContext);
        HttpManager.getDataPagingInfo(mContext, packagename, VersionCode, AppName, new HttpRequestHandler<Result>() {
            @Override
            public void onSuccess(Result data) {
                if(data != null){
                    if (data.getCode() == 200){
                        ArrayList<AppResult> appResultlist = JSONObject.parseObject(String.valueOf(data.getObject()), new TypeReference<ArrayList<AppResult>>() {});
                        AppResult appResult = appResultlist.get(0);
                        showDialog(appResult);

                    }else if (data.getCode() == 100){
                        Toast.makeText(mContext,data.getMessage(), Toast.LENGTH_SHORT).show();
                        jumpLoginActivity();
                    }else if (data.getCode() == 999){
                        Toast.makeText(mContext,data.getMessage(), Toast.LENGTH_SHORT).show();
                        jumpLoginActivity();
                    }
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
    private void showDialog(final AppResult appResult) {
        if (appResult.getLastforce().equals("0")){
            new CommomDialog(mContext, R.style.dialog, appResult.getUpgradeinfo(),true, new CommomDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm == true){
                        UpdateManager manager = new UpdateManager(mContext,appResult.getUpdateurl());
                        manager.showDownloadDialog();
                    }else {
                        jumpLoginActivity();
                    }
                    dialog.dismiss();
                }
            }).setTitle("发现新的版本").show();
        }else if (appResult.getLastforce().equals("1")){
            new CommomDialog(mContext, R.style.dialog, appResult.getUpgradeinfo(),false, new CommomDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm == true){
                        UpdateManager manager = new UpdateManager(mContext,appResult.getUpdateurl());
                        manager.showDownloadDialog();
                    }else {
                        jumpLoginActivity();
                    }
                    dialog.dismiss();
                }
            }).setTitle("发现新的版本").show();
        }

    }
    /**
     * 跳转至登录界面*
     */
    private void jumpLoginActivity() {
        Intent intent = new Intent();
        try {
            intent.setClass(mContext, Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mContext.startActivity(intent);
//        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }
}
