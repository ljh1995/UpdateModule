package com.example.administrator.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by wxs on 2018/4/27.
 */

public class CommomDialog extends Dialog implements View.OnClickListener{
private TextView contentTxt;
private TextView titleTxt;
private TextView submitTxt;
private TextView cancelTxt;
private LinearLayout Linercancel;

private Context mContext;
private String content;
private OnCloseListener listener;
private String positiveName;
private String negativeName;
private String title;
private boolean flag;

public CommomDialog(Context context) {
        super(context);
        this.mContext = context;
        }

public CommomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        }

public CommomDialog(Context context, int themeResId, String content,boolean flag, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.flag = flag;
        this.listener = listener;
        }

protected CommomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        }

public CommomDialog setTitle(String title){
        this.title = title;
        return this;
        }

public CommomDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
        }

public CommomDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
        }

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom);
        setCanceledOnTouchOutside(false);
        initView();
        }

private void initView(){
        contentTxt = (TextView)findViewById(R.id.content);
        titleTxt = (TextView)findViewById(R.id.title);
        submitTxt = (TextView)findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.cancel);
        Linercancel = (LinearLayout)findViewById(R.id.liner_canael);
        cancelTxt.setOnClickListener(this);

        contentTxt.setText(content);
        if(!TextUtils.isEmpty(positiveName)){
        submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(negativeName)){
        cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
        titleTxt.setText(title);
        }

        if (flag == true){
                Linercancel.setVisibility(View.VISIBLE);
        }else {
                Linercancel.setVisibility(View.GONE);
        }
        }

@Override
public void onClick(View v) {
        if (v.getId() == R.id.cancel) {
                if (listener != null) {
                        listener.onClick(this, false);
                }
                this.dismiss();
        } else if (v.getId() == R.id.submit) {
                if (listener != null) {
                        listener.onClick(this, true);
                }
        }
}

public interface OnCloseListener{
    void onClick(Dialog dialog, boolean confirm);
}
}
