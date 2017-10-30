package com.topsports.pictureServer.util;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by huang.cj on 2017/8/30.
 */
public class OutputJson implements Serializable {


    /**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //状态码
    private int errorCode;
    //必要的提示信息
    private String message;
    //业务数据
    private Object data;


    public OutputJson(int errorCode,String message,Object data){
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String toString(){
        if(null == this.data){
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
