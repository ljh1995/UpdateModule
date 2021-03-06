package com.example.administrator.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/1/11 0011.
 */

public class InitActivity extends AppCompatActivity {
    String packagename,AppName;
    int VersionCode;
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        mPermissionsChecker = new PermissionsChecker(this);

    }
    class splashhandler implements Runnable {

        public void run() {
            update();
//            jumpLoginActivity();
        }

    }

    /**
     * 跳转至登录界面*
     */
    private void jumpLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }
    @Override
    protected void onResume() {
        super.onResume();

//         缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        } else {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Handler x = new Handler();
            x.postDelayed(new splashhandler(), 2000);
        }

    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
    private void update() {
        packagename = PackageUtils.getPackageName(this);
        VersionCode = PackageUtils.getVersionCode(this);
        AppName = PackageUtils.getAppName(this);
        UpdateUtils updateUtils = new UpdateUtils(this,packagename,AppName,VersionCode,"");
        updateUtils.update(new CallBack() {
            @Override
            public void callback(Result result) {
                if (result.getCode() == 200){
                    showDialog((AppResult) result.getObject());
                }else if (result.getCode() == 100){
                    Toast.makeText(InitActivity.this,result.getMessage(), Toast.LENGTH_SHORT).show();
                    jumpLoginActivity();
                }else {
                    Toast.makeText(InitActivity.this,result.getMessage(), Toast.LENGTH_SHORT).show();
                    jumpLoginActivity();
                }
            }
        });
    }
    private void showDialog(final AppResult appResult) {
        if (appResult.getLastforce().equals("0")){
            new CommomDialog(InitActivity.this, R.style.dialog, appResult.getUpgradeinfo(),true, new CommomDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm == true){
                        UpdateManager manager = new UpdateManager(InitActivity.this,appResult.getUpdateurl(),"");
                        manager.showDownloadDialog();
                    }else {
                        jumpLoginActivity();
                    }
                    dialog.dismiss();
                }
            }).setTitle("发现新的版本").show();
        }else if (appResult.getLastforce().equals("1")){
            new CommomDialog(InitActivity.this, R.style.dialog, appResult.getUpgradeinfo(),false, new CommomDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm == true){
                        UpdateManager manager = new UpdateManager(InitActivity.this,appResult.getUpdateurl(),"");
                        manager.showDownloadDialog();
                    }else {
                        jumpLoginActivity();
                    }
                    dialog.dismiss();
                }
            }).setTitle("发现新的版本").show();
        }

    }
}
