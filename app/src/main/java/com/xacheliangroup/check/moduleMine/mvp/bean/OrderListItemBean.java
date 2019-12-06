package com.xacheliangroup.check.moduleMine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/2/28,10:49
 */
public class OrderListItemBean extends BaseBean {
    /**
     * buyCount : 2
     * mdseDes : 玻璃水是什么呢，不用小编多说相信很多人都知道吧，那就是清洁汽车挡风玻璃的液体。那又有多少人知道玻璃水要怎么用呢？玻璃水一般分为3种，一种夏季用，夏季虫子比较多，玻璃水里面含有除虫胶，可以快速的清楚玻璃上的虫子尸体残留；一种呢是冬季用，冬季天气冷，有些地区低于零摄氏度会结冰，冬季用的玻璃水含有防冻的物质，外界气温在零下20度以上可以使用。还有一种呢，是适用于很冷很冷的地方用的，可以防止结冰。
     * mdseId : 261
     * mdseName : 玻璃水
     * mdsePhoto : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdse/1551145332881.png
     * orderId : 2
     * orderMoney : 70.00
     * orderNumb : 22222222
     * orderType : 1
     * payMoney : 70.00
     * paymentType : 1
     * storeName : 车宝宝平台
     */

    private String buyCount;
    private String mdseDes;
    private String mdseId;
    private String mdseName;
    private String mdsePhoto;
    private int orderId;
    private String orderMoney;
    private String orderNumb;
    private String orderType;
    private String payMoney;
    private String paymentType;
    private String storeName;

    @Override
    public int getViewType() {
        return ViewItemType.ORDER_LIST_ITEM_VIEW;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getMdseDes() {
        return mdseDes;
    }

    public void setMdseDes(String mdseDes) {
        this.mdseDes = mdseDes;
    }

    public String getMdseId() {
        return mdseId;
    }

    public void setMdseId(String mdseId) {
        this.mdseId = mdseId;
    }

    public String getMdseName() {
        return mdseName;
    }

    public void setMdseName(String mdseName) {
        this.mdseName = mdseName;
    }

    public String getMdsePhoto() {
        return mdsePhoto;
    }

    public void setMdsePhoto(String mdsePhoto) {
        this.mdsePhoto = mdsePhoto;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderNumb() {
        return orderNumb;
    }

    public void setOrderNumb(String orderNumb) {
        this.orderNumb = orderNumb;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
