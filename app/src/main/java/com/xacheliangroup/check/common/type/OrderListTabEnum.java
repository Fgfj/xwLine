package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/3/1,11:43
 */
public enum OrderListTabEnum implements Serializable {
    //订单状态  “”全部   1-待付款 2-待发货 3-待收货 4-待评价
    TAB_ALL_ORDER(0,"全部",""),
    TAB_WAIT_PAY_ORDER(1,"待付款","1"),
    TAB_WAIT_SEND_ORDER(2,"待发货","2"),
    TAB_WAIT_GET_ORDER(3,"待收货","3"),
    TAB_WAIT_COMMENT_ORDER(4,"待评价","4");
    private int tabPosition;
    private String explain;
    private String orderType;

    OrderListTabEnum(int tabPostion, String explain, String orderType) {
        this.tabPosition = tabPostion;
        this.explain = explain;
        this.orderType = orderType;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public static String getSelectTypeByPosition(int position) {
        for (OrderListTabEnum enumItem : OrderListTabEnum.values()) {
            if (enumItem.getTabPosition()==position) {
                return enumItem.getOrderType();
            }
        }
        return "";
    }
}
