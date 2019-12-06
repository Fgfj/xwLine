package com.xacheliangroup.check.moduleMall.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2018/12/21,11:05
 */
public class SellerItemBean extends BaseBean {
    /**
     * address : 科技六路
     * minli : 0.0
     * monthnumber : 0
     * pushlabel1 : 有优惠1
     * pushlabel2 : 折扣促销中1
     * pushlabel3 : 抽奖活动1
     * starnumber : 0.0
     * storefrontid : 78
     * storefrontname : 车宝宝CE
     * storephoto : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/bussisphoto/1544409745416.jpg
     * storetype : 2
     */

    private String address;
    private String minli;
    private String monthnumber;
    private String pushlabel1;
    private String pushlabel2;
    private String pushlabel3;
    private String starnumber;
    private String storefrontid;
    private String storefrontname;
    private String storephoto;
    private String storetype;

    @Override
    public int getViewType() {
        return ViewItemType.SELLER_HOME_ITEM_VIEW;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMinli() {
        return minli;
    }

    public void setMinli(String minli) {
        this.minli = minli;
    }

    public String getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(String monthnumber) {
        this.monthnumber = monthnumber;
    }

    public String getPushlabel1() {
        return pushlabel1;
    }

    public void setPushlabel1(String pushlabel1) {
        this.pushlabel1 = pushlabel1;
    }

    public String getPushlabel2() {
        return pushlabel2;
    }

    public void setPushlabel2(String pushlabel2) {
        this.pushlabel2 = pushlabel2;
    }

    public String getPushlabel3() {
        return pushlabel3;
    }

    public void setPushlabel3(String pushlabel3) {
        this.pushlabel3 = pushlabel3;
    }

    public String getStarnumber() {
        return starnumber;
    }

    public void setStarnumber(String starnumber) {
        this.starnumber = starnumber;
    }

    public String getStorefrontid() {
        return storefrontid;
    }

    public void setStorefrontid(String storefrontid) {
        this.storefrontid = storefrontid;
    }

    public String getStorefrontname() {
        return storefrontname;
    }

    public void setStorefrontname(String storefrontname) {
        this.storefrontname = storefrontname;
    }

    public String getStorephoto() {
        return storephoto;
    }

    public void setStorephoto(String storephoto) {
        this.storephoto = storephoto;
    }

    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
    }
}
