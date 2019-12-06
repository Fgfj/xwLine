package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/2/22,17:15
 */
public enum MarketingEnum implements Serializable {
    SELLER_LIST(1,"商家列表"),
    GOODS_LIST(2,"商品列表");

    MarketingEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;

    private String desc;

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
