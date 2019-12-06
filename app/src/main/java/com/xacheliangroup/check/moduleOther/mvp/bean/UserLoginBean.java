package com.xacheliangroup.check.moduleOther.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2018/12/25,16:14
 */
public class UserLoginBean extends BaseBean {

    /**
     * appuserid : 94
     * mobile : 13683498647
     * signature : 测试个性签名修改
     * username : 司马缸砸光8
     * userpic : http://jzcar.oss-cn-zhangjiakou.aliyuncs.com/uhead/153432874052494.jpg
     */

    private int appuserid;
    private String mobile;
    private String signature;
    private String username;
    private String userpic;

    public int getAppuserid() {
        return appuserid;
    }

    public void setAppuserid(int appuserid) {
        this.appuserid = appuserid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }
}
