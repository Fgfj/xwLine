package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2018/12/20,10:41
 */
public enum BannerEnum implements Serializable {
    OTHER_BANNER(0,"未知banner"),
    MALL_TOP_BANNER(1,"商城首页top轮播"),
    MALL_GROOM_BANNER(2,"商场推荐轮播"),
    IMG_BANNER(3,"查看图片");

    private int type;
    private String explain;

    BannerEnum(int type, String explain) {
        this.type = type;
        this.explain = explain;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
