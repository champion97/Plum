package org.java10.plum.models.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Tate on 2017/1/5.
 */
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 8087429191584388924L;

    private int code = -1;
    private String msg;
    private T data;
    private long timestamp = System.currentTimeMillis();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RestResponse() {

    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(Integer code) {
        this.code = code;
    }

    public RestResponse(int code,T data) {
        this.data = data;
        this.code = code;
    }

    public RestResponse(String msg) {
        this.msg = msg;
    }

    public RestResponse(int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(int code, String msg, T data ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResponse(String msg, T data ) {
        this.msg = msg;
        this.data = data;
    }

    public static RestResponse success() {
        return new RestResponse(0);
    }

    public static <T> RestResponse success(T data) {
        return new RestResponse(0, data);
    }

    public static RestResponse success(int code) {
        return new RestResponse(code);
    }

    public static <T> RestResponse success(String msg, T data ) {
        return new RestResponse(msg, data);
    }

    public static <T> RestResponse success(int code, T data) {
        return new RestResponse(code, data);
    }

    public static RestResponse fail() {
        return new RestResponse(false);
    }

    public static RestResponse fail(int code) {
        return new RestResponse(code);
    }

    public static RestResponse fail(String msg) {
        return new RestResponse(msg);
    }

    public static RestResponse fail(int code, String msg) {
        return new RestResponse(code, msg);
    }
    public static <T> RestResponse fail(int code, String msg, T data) {
        return new RestResponse(code, msg, data);
    }

    public String toJSONString(){
        return JSON.toJSONString(this);
    }
}
