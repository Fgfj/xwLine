package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/2/28,10:26
 */
public enum MineMenuEnum implements Serializable {
    CARD_LIST(1,"我的卡卷"),
    ORDER_LIST(2,"我的订单"),
    BILL_LIST(3,"我的发票"),
    DOPE_LIST(4,"我的消息"),
    ADDRESS_LIST(5,"地址设置"),
    SYSTEM_SETTING(6,"系统设置");
    private Integer value;

    private String desc;

    MineMenuEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
