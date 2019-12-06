package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/3/1,11:21
 */
public enum ChangeOrderTypeOperateEnum implements Serializable {
    CANCEL_ORDER_CHANGE(1,"取消订单"),
    DELETE_ORDER_CHANGE(2,"删除订单"),
    PAY_ORDER_CHANGE(3,"需付款"),
    GET_ORDER_CHANGE(4,"确认收货"),
    COMMENT_ORDER_CHANGE(5,"去评价"),
    NEW_ORDER_CHANGE(6,"重新下单");
    private int changeType;
    private String explain;

    ChangeOrderTypeOperateEnum(int changeType, String explain) {
        this.changeType = changeType;
        this.explain = explain;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
