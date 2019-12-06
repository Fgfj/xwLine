package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/2/27,10:27
 */
public enum AddressEnum implements Serializable {
    ADD_ADDRESS(1,"新增地址"),
    UPDATE_ADDRESS(2,"修改地址"),
    SETTING_IN(3,"地址设置进入"),
    PAY_ORDER_IN(4,"下单进入");
    private int value;
    private String explain;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    AddressEnum(int value, String explain) {
        this.value = value;
        this.explain = explain;
    }
}
