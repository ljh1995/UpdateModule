package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2017/12/20.
 */

public interface HttpRequestHandler <E> {
    public void onSuccess(E data);

    public void onSuccess(E data, int totalPages, int currentPage);

    public void onFailure(String error);
}