package com.xacheliangroup.check.common.http.okhttp;

import android.text.TextUtils;

/**
 * Created by yangshuai on 2017/8/19.
 * {link http://afra55.github.io}
 * 一个通用接口返回格式
 */
public class ResultBean {

    private static final String HTTP_SERVICE_SUCCESS = "1";

    private String status;

    private String msg;

    private String data;
    private Integer regdate;

    private String state;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getRegdate() {
        return regdate;
    }

    public void setRegdate(Integer regdate) {
        this.regdate = regdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isSuccess() {
        return !TextUtils.isEmpty(status) && status.endsWith(HTTP_SERVICE_SUCCESS) && !state.equals("fail");
    }
}
