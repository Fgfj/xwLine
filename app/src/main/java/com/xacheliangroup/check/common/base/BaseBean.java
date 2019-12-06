package com.xacheliangroup.check.common.base;

import android.text.TextUtils;


import com.xacheliangroup.check.common.flag.Flag;

import java.io.Serializable;


/**
 * Created by Afra55 on 2017/10/9.
 * Smile is the best name card.
 * 所有的 bean 文件需要继承 BaseBean
 */

public class BaseBean implements Serializable {

    private int viewType = Flag.DefaultData.none;
    /**
     * code : 200
     * message : 发送成功
     */

    private int code;
    private String message;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
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

    public boolean is200Ok() {
        return code == Flag.HTTP.code_success;
    }

    protected String checkEmptyData(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }
}
