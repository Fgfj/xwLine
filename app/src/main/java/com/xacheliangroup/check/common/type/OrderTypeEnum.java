package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/3/1,10:33
 */
public enum OrderTypeEnum implements Serializable {
    ORDER_CANCEL("0","订单已取消","",ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE,"订单已取消",OrderDetailBottomType.TWO_BUTTON,null,ChangeOrderTypeOperateEnum.DELETE_ORDER_CHANGE,ChangeOrderTypeOperateEnum.NEW_ORDER_CHANGE),
    ORDER_WAIT_PAY("1","待付款","去支付",ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE,"等待付款",OrderDetailBottomType.TWO_BUTTON,null,
            ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE,ChangeOrderTypeOperateEnum.PAY_ORDER_CHANGE),
    ORDER_WAIT_SEND("2","待发货","",null,"您的订单已付款，发货处理中",OrderDetailBottomType.CANCEL_BUTTON,null,null,null),
    ORDER_WAIT_GET("3","待收货","确认收货",null,"您的订单已发货",OrderDetailBottomType.ONE_BUTTON,ChangeOrderTypeOperateEnum.GET_ORDER_CHANGE,null,null),
    ORDER_WAIT_COMMENT("4","待评价","去评价",null,"您的订单已签收",OrderDetailBottomType.ONE_BUTTON,ChangeOrderTypeOperateEnum.COMMENT_ORDER_CHANGE,null,null),
    ORDER_COMPLETE("5","已完成","",ChangeOrderTypeOperateEnum.DELETE_ORDER_CHANGE,"订单已完成",OrderDetailBottomType.ONE_BUTTON,ChangeOrderTypeOperateEnum.DELETE_ORDER_CHANGE,null,null);
    private String type;
    private String explain;
    private String orderListOnclickTx;
    private ChangeOrderTypeOperateEnum mChangeOperateEnum;//订单列表功能
    private String orderDetailTopTitle;
    private OrderDetailBottomType orderDetailBottomType;//底部按钮分类
    private ChangeOrderTypeOperateEnum oneButtonChangeOrder;//长按钮功能
    private ChangeOrderTypeOperateEnum twoButtonChangeOrder;//左边按钮功能
    private ChangeOrderTypeOperateEnum threeButtonChangeOrder;//右边按钮功能

    OrderTypeEnum(String type, String explain, String orderListOnclickTx, ChangeOrderTypeOperateEnum mChangeOperateEnum, String orderDetailTopTitle, OrderDetailBottomType orderDetailBottomType, ChangeOrderTypeOperateEnum oneButtonChangeOrder, ChangeOrderTypeOperateEnum twoButtonChangeOrder, ChangeOrderTypeOperateEnum threeButtonChangeOrder) {
        this.type = type;
        this.explain = explain;
        this.orderListOnclickTx = orderListOnclickTx;
        this.mChangeOperateEnum = mChangeOperateEnum;
        this.orderDetailTopTitle = orderDetailTopTitle;
        this.orderDetailBottomType = orderDetailBottomType;
        this.oneButtonChangeOrder = oneButtonChangeOrder;
        this.twoButtonChangeOrder = twoButtonChangeOrder;
        this.threeButtonChangeOrder = threeButtonChangeOrder;
    }

    public OrderDetailBottomType getOrderDetailBottomType() {
        return orderDetailBottomType;
    }

    public void setOrderDetailBottomType(OrderDetailBottomType orderDetailBottomType) {
        this.orderDetailBottomType = orderDetailBottomType;
    }

    public ChangeOrderTypeOperateEnum getOneButtonChangeOrder() {
        return oneButtonChangeOrder;
    }

    public void setOneButtonChangeOrder(ChangeOrderTypeOperateEnum oneButtonChangeOrder) {
        this.oneButtonChangeOrder = oneButtonChangeOrder;
    }

    public ChangeOrderTypeOperateEnum getTwoButtonChangeOrder() {
        return twoButtonChangeOrder;
    }

    public void setTwoButtonChangeOrder(ChangeOrderTypeOperateEnum twoButtonChangeOrder) {
        this.twoButtonChangeOrder = twoButtonChangeOrder;
    }

    public ChangeOrderTypeOperateEnum getThreeButtonChangeOrder() {
        return threeButtonChangeOrder;
    }

    public void setThreeButtonChangeOrder(ChangeOrderTypeOperateEnum threeButtonChangeOrder) {
        this.threeButtonChangeOrder = threeButtonChangeOrder;
    }

    public String getOrderListOnclickTx() {
        return orderListOnclickTx;
    }

    public void setOrderListOnclickTx(String orderListOnclickTx) {
        this.orderListOnclickTx = orderListOnclickTx;
    }

    OrderTypeEnum(String type, String explain) {
        this.type = type;
        this.explain = explain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
    public String getOrderDetailTopTitle() {
        return orderDetailTopTitle;
    }

    public void setOrderDetailTopTitle(String orderDetailTopTitle) {
        this.orderDetailTopTitle = orderDetailTopTitle;
    }


    public ChangeOrderTypeOperateEnum getmChangeOperateEnum() {
        return mChangeOperateEnum;
    }

    public void setmChangeOperateEnum(ChangeOrderTypeOperateEnum mChangeOperateEnum) {
        this.mChangeOperateEnum = mChangeOperateEnum;
    }

    /**
     * 获取订单类型enum
     * @param type
     * @return
     */
    public static OrderTypeEnum getTypeEnumByType(String type) {
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem;
            }
        }
        return OrderTypeEnum.ORDER_CANCEL;
    }

    /**
     * 获取订单状态对应解释  列表上不展现订单状态文字
     * @param type
     * @return
     */
    public static String getTypeEnumTxByType(String type) {
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.explain;
            }
        }
        return "";
    }
    /**
     * 获取订单列表点击文字
     * @param type
     * @return
     */
    public static String getOrderListOnclickTxByType(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getOrderListOnclickTx();
            }
        }
        return "";
    }

    /**
     * 根据订单状态获取可以对订单的操作
     * @param type
     * @return
     */
    public static ChangeOrderTypeOperateEnum getChangeOpreateByType(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getmChangeOperateEnum();
            }
        }
        return null;
    }

    /**
     * 根据订单编号获取订单详情头部文字
     * @param type
     * @return
     */
    public static String getOrderDetailTopTilteTxByType(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getOrderDetailTopTitle();
            }
        }
        return "";
    }

    /**
     * 根据订单状态显示底部状态
     * @param type
     * @return
     */
    public static OrderDetailBottomType getBotttonShowType(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getOrderDetailBottomType();
            }
        }
        return OrderDetailBottomType.ONE_BUTTON;
    }
    /**
     * 获取一个按钮的功能
     * @param type
     * @return
     */
    public static ChangeOrderTypeOperateEnum getOne(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getOneButtonChangeOrder();
            }
        }
        return ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE;
    }
    /**
     * 获取二个按钮的功能 left
     * @param type
     * @return
     */
    public static ChangeOrderTypeOperateEnum getTwo(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getTwoButtonChangeOrder();
            }
        }
        return ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE;
    }
    /**
     * 获取三个按钮的功能 right
     * @param type
     * @return
     */
    public static ChangeOrderTypeOperateEnum getThree(String type){
        for (OrderTypeEnum enumItem : OrderTypeEnum.values()) {
            if (enumItem.getType().equals(type)) {
                return enumItem.getThreeButtonChangeOrder();
            }
        }
        return ChangeOrderTypeOperateEnum.CANCEL_ORDER_CHANGE;
    }
}
