package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/9/3,14:21
 */
public class TicketBean extends BaseBean {
    @Override
    public String toString() {
        return "TicketBean{" +
                "ticketid=" + ticketid +
                ", userid=" + userid +
                ", orderid=" + orderid +
                ", fareid=" + fareid +
                ", ticketno='" + ticketno + '\'' +
                ", ticketprice=" + ticketprice +
                ", createtime='" + createtime + '\'' +
                ", status=" + status +
                ", updatetime='" + updatetime + '\'' +
                ", palxslfare=" + palxslfare +
                '}';
    }

    /**
     * ticketid : 13
     * userid : 6
     * orderid : 17
     * fareid : 3
     * ticketno : D9915672354170705
     * ticketprice : 50
     * createtime : 2019-08-31 15:10:17
     * status : 1
     * updatetime : 2019-08-31 15:10:17
     * palxslfare : null
     */

    private int ticketid;
    private int userid;
    private int orderid;
    private int fareid;
    private String ticketno;
    private int ticketprice;
    private String createtime;
    private int status;
    private String updatetime;
    private Object palxslfare;

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getFareid() {
        return fareid;
    }

    public void setFareid(int fareid) {
        this.fareid = fareid;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public int getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(int ticketprice) {
        this.ticketprice = ticketprice;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Object getPalxslfare() {
        return palxslfare;
    }

    public void setPalxslfare(Object palxslfare) {
        this.palxslfare = palxslfare;
    }
}
