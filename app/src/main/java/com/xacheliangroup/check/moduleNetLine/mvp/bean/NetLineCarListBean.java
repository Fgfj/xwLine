package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/9/3,14:42
 */
public class NetLineCarListBean extends BaseBean {

    @Override
    public int getViewType() {
        return ViewItemType.NET_LINE_ROUTE;
    }

    /**
     * lineid : 2
     * linename : 西安-渭南
     * price : 50
     * createtime : 2019-08-27 14:55:33
     * starttime : 6
     * endtime : 20
     * delaytime : 30
     * status : 1
     * backpoundage : 5
     * startcityid : 1
     * endcityid : 2
     * startcity : null
     * endcity : null
     */

    private int lineid;
    private String linename;
    private int price;
    private String createtime;
    private String starttime;
    private String endtime;
    private int delaytime;
    private int status;
    private int backpoundage;
    private int startcityid;
    private int endcityid;
    private String startcity;
    private String endcity;

    public int getLineid() {
        return lineid;
    }

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(int delaytime) {
        this.delaytime = delaytime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBackpoundage() {
        return backpoundage;
    }

    public void setBackpoundage(int backpoundage) {
        this.backpoundage = backpoundage;
    }

    public int getStartcityid() {
        return startcityid;
    }

    public void setStartcityid(int startcityid) {
        this.startcityid = startcityid;
    }

    public int getEndcityid() {
        return endcityid;
    }

    public void setEndcityid(int endcityid) {
        this.endcityid = endcityid;
    }

    public String getStartcity() {
        return startcity;
    }

    public void setStartcity(String startcity) {
        this.startcity = startcity;
    }

    public String getEndcity() {
        return endcity;
    }

    public void setEndcity(String endcity) {
        this.endcity = endcity;
    }
}
