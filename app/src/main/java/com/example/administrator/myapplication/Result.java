package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2019/1/11 0011.
 */

public class Result {
    private int code;

    private String message;

    private Object object;

    public Result(){
        ;
    }

    public Result(int code, String message, Object object){
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
