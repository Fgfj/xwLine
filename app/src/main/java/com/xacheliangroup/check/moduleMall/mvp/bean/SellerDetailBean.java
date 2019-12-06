package com.xacheliangroup.check.moduleMall.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

import java.util.List;

/**
 * author:yz
 * data: 2019/2/22,16:46
 */
public class SellerDetailBean extends BaseBean{

    /**
     * storeAddress : 国宾中央区
     * storeBannerList : ["http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/bussisphoto/1550656969314.png","http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/bussisphoto/1550656969593.png"]
     * storeFrontName : 沙县小吃
     * storeLat : 34.20805
     * storeLong : 108.872553
     * storeMinLi : 36.01
     * storePhoneNumber : 13619285555
     * storePushLabel1 :
     * storePushLabel2 :
     * storePushLabel3 :
     * storeStarNumber : 0.0
     */

    private String storeAddress;
    private String storeFrontName;
    private String storeLat;
    private String storeLong;
    private String storeMinLi;
    private String storePhoneNumber;
    private String storePushLabel1;
    private String storePushLabel2;
    private String storePushLabel3;
    private String storeStarNumber;
    private List<String> storeBannerList;

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }

    public String getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(String storeLat) {
        this.storeLat = storeLat;
    }

    public String getStoreLong() {
        return storeLong;
    }

    public void setStoreLong(String storeLong) {
        this.storeLong = storeLong;
    }

    public String getStoreMinLi() {
        return storeMinLi;
    }

    public void setStoreMinLi(String storeMinLi) {
        this.storeMinLi = storeMinLi;
    }

    public String getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public void setStorePhoneNumber(String storePhoneNumber) {
        this.storePhoneNumber = storePhoneNumber;
    }

    public String getStorePushLabel1() {
        return storePushLabel1;
    }

    public void setStorePushLabel1(String storePushLabel1) {
        this.storePushLabel1 = storePushLabel1;
    }

    public String getStorePushLabel2() {
        return storePushLabel2;
    }

    public void setStorePushLabel2(String storePushLabel2) {
        this.storePushLabel2 = storePushLabel2;
    }

    public String getStorePushLabel3() {
        return storePushLabel3;
    }

    public void setStorePushLabel3(String storePushLabel3) {
        this.storePushLabel3 = storePushLabel3;
    }

    public String getStoreStarNumber() {
        return storeStarNumber;
    }

    public void setStoreStarNumber(String storeStarNumber) {
        this.storeStarNumber = storeStarNumber;
    }

    public List<String> getStoreBannerList() {
        return storeBannerList;
    }

    public void setStoreBannerList(List<String> storeBannerList) {
        this.storeBannerList = storeBannerList;
    }
}
