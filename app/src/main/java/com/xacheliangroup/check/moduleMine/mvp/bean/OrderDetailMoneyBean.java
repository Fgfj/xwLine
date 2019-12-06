package com.xacheliangroup.check.moduleMine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/2/28,16:23
 */
public class OrderDetailMoneyBean extends BaseBean {
    @Override
    public int getViewType() {
        return ViewItemType.ORDER_DETAIL_MONEY_ITEM_VIEW;
    }
    private String title;
    private String money;
    private boolean isShowLine;

    public OrderDetailMoneyBean(String title, String money, boolean isShowLine) {
        this.title = title;
        this.money = money;
        this.isShowLine = isShowLine;
    }

    public boolean isShowLine() {
        return isShowLine;
    }

    public void setShowLine(boolean showLine) {
        isShowLine = showLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
