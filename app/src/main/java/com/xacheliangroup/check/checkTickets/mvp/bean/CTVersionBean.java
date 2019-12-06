package com.xacheliangroup.check.checkTickets.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/6/11,10:09
 */
public class CTVersionBean extends BaseBean {

    /**
     * editionCode : 1
     * editionNumber : 1.0
     * editionTitle : 修改了一些BUG
     * editionUrl : http://47.92.114.238:21001/4S-web/zcar.apk
     * ifForcedUpdate : 1
     * phoneXh : android
     */

    private String editionCode;
    private String editionNumber;
    private String editionTitle;
    private String editionUrl;
    private String ifForcedUpdate;
    private String phoneXh;

    public String getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(String editionCode) {
        this.editionCode = editionCode;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getEditionTitle() {
        return editionTitle;
    }

    public void setEditionTitle(String editionTitle) {
        this.editionTitle = editionTitle;
    }

    public String getEditionUrl() {
        return editionUrl;
    }

    public void setEditionUrl(String editionUrl) {
        this.editionUrl = editionUrl;
    }

    public String getIfForcedUpdate() {
        return ifForcedUpdate;
    }

    public void setIfForcedUpdate(String ifForcedUpdate) {
        this.ifForcedUpdate = ifForcedUpdate;
    }

    public String getPhoneXh() {
        return phoneXh;
    }

    public void setPhoneXh(String phoneXh) {
        this.phoneXh = phoneXh;
    }
}
