package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/3/1,16:11
 */
public enum  OrderDetailBottomType implements Serializable {
    ONE_BUTTON(1,"一个按钮"),
    TWO_BUTTON(1,"二个按钮"),
    CANCEL_BUTTON(1,"没有按钮");
    private int bottomType;
    private String vaule;

    OrderDetailBottomType(int bottomType, String vaule) {
        this.bottomType = bottomType;
        this.vaule = vaule;
    }

    public int getBottomType() {
        return bottomType;
    }

    public void setBottomType(int bottomType) {
        this.bottomType = bottomType;
    }

    public String getVaule() {
        return vaule;
    }

    public void setVaule(String vaule) {
        this.vaule = vaule;
    }
}
