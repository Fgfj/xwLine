package com.xacheliangroup.check.moduleMine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/2/26,16:41
 */
public class AddressItemBean extends BaseBean{

    /**
     * address : 多喝点比你小你从哪才能从哪从哪从哪从哪出陈娜娜擦擦你减肥减肥
     * addressId : 116
     * appUserId : 94
     * ifDefault : 1
     * name : 复活甲发酒疯
     * phoneNumber : 15891162137
     */

    private String address;
    private String addressId;
    private int appUserId;
    private String ifDefault;
    private String name;
    private String phoneNumber;

    @Override
    public int getViewType() {
        return ViewItemType.MINE_ADDRESS_ITEM_VIEW;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getIfDefault() {
        return ifDefault;
    }

    public void setIfDefault(String ifDefault) {
        this.ifDefault = ifDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
