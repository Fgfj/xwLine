package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2018/12/20,09:47
 */
public enum WebEnum implements Serializable {
    OTHER_WEB(0,"其他页面"),
    MALL_HOME_BANNER(1,"商城首页轮播"),
    MALL_HOME_SUPER(2,"超级推荐");

    private Integer value;

    private String desc;

    WebEnum(Integer value, String desc) {
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
