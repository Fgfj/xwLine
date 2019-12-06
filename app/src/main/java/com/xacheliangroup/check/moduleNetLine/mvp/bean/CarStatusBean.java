package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/9/3,15:01
 */
public class CarStatusBean extends BaseBean {

    /**
     * recordid : 9
     * carid : 3
     * carnumber : 陕A10001
     * driverid : 3
     * realname : 王五
     * busnumber : 0
     * starttime : null
     * endtime : 2019-09-03 15:56:45
     * ticketids : null
     * orderids : null
     * status : 3
     * createtime : 2019-09-03 14:56:36
     * startcityid : 1
     * endcityid : 2
     * startcityname : 西安
     * endcityname : 渭南
     * lineid : 2
     * palxSlline : null
     */

    private int recordid;
    private int carid;
    private String carnumber;
    private int driverid;
    private String realname;
    private int busnumber;
    private Object starttime;
    private String endtime;
    private Object ticketids;
    private Object orderids;
    private int status;
    private String createtime;
    private int startcityid;
    private int endcityid;
    private String startcityname;
    private String endcityname;
    private int lineid;
    private Object palxSlline;

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(int busnumber) {
        this.busnumber = busnumber;
    }

    public Object getStarttime() {
        return starttime;
    }

    public void setStarttime(Object starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Object getTicketids() {
        return ticketids;
    }

    public void setTicketids(Object ticketids) {
        this.ticketids = ticketids;
    }

    public Object getOrderids() {
        return orderids;
    }

    public void setOrderids(Object orderids) {
        this.orderids = orderids;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public String getStartcityname() {
        return startcityname;
    }

    public void setStartcityname(String startcityname) {
        this.startcityname = startcityname;
    }

    public String getEndcityname() {
        return endcityname;
    }

    public void setEndcityname(String endcityname) {
        this.endcityname = endcityname;
    }

    public int getLineid() {
        return lineid;
    }

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public Object getPalxSlline() {
        return palxSlline;
    }

    public void setPalxSlline(Object palxSlline) {
        this.palxSlline = palxSlline;
    }
}
