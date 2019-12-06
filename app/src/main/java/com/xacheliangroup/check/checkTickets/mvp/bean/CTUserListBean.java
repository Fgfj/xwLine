package com.xacheliangroup.check.checkTickets.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/5/15,23:57
 */
public class CTUserListBean extends BaseBean {

    @Override
    public int getViewType() {
        return ViewItemType.CHECK_TICKETS_USER_LIST;
    }

    private boolean isShowRl;
    /**
     * arriveName : 汉阴(高速)
     * checkType : 0
     * checkid : 136
     * checktime : 2019-06-13 17:19:46
     * clasDateTime : 2019-05-13 18:00
     * classdate : 2019-05-13
     * classid : 1188193
     * idtype : 1
     * insuranceIdCard : 610621199110190028
     * insurancedName : 李雪梅
     * isfree : 0
     * lineid : 176
     * operIdCard : null
     * opercode : null
     * operid : null
     * opername : null
     * opertype : null
     * ownerStationName : 城南客运站
     * price : 86.5
     * seat : null
     * sn : 1801CA888589
     * stationid : 1002
     * sumid : 11257051
     * ticketBill : 14760141
     * ticketID : 41193444
     */

    private String arriveName;
    private String checkType;
    private int checkid;
    private String checktime;
    private String clasDateTime;
    private String classdate;
    private String classid;
    private String idtype;
    private String insuranceIdCard;
    private String insurancedName;
    private String isfree;
    private String lineid;
    private Object operIdCard;
    private Object opercode;
    private Object operid;
    private Object opername;
    private Object opertype;
    private String ownerStationName;
    private String price;
    private String sn;
    private String stationid;
    private String sumid;
    private String ticketBill;
    private String ticketID;
    private String seat;

    private String checkBill;

    public String getCheckBill() {
        return checkBill;
    }

    public void setCheckBill(String checkBill) {
        this.checkBill = checkBill;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public boolean isShowRl() {
        return isShowRl;
    }

    public void setShowRl(boolean showRl) {
        isShowRl = showRl;
    }


    public String getArriveName() {
        return arriveName;
    }

    public void setArriveName(String arriveName) {
        this.arriveName = arriveName;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public int getCheckid() {
        return checkid;
    }

    public void setCheckid(int checkid) {
        this.checkid = checkid;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getClasDateTime() {
        return clasDateTime;
    }

    public void setClasDateTime(String clasDateTime) {
        this.clasDateTime = clasDateTime;
    }

    public String getClassdate() {
        return classdate;
    }

    public void setClassdate(String classdate) {
        this.classdate = classdate;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getInsuranceIdCard() {
        return insuranceIdCard;
    }

    public void setInsuranceIdCard(String insuranceIdCard) {
        this.insuranceIdCard = insuranceIdCard;
    }

    public String getInsurancedName() {
        return insurancedName;
    }

    public void setInsurancedName(String insurancedName) {
        this.insurancedName = insurancedName;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public Object getOperIdCard() {
        return operIdCard;
    }

    public void setOperIdCard(Object operIdCard) {
        this.operIdCard = operIdCard;
    }

    public Object getOpercode() {
        return opercode;
    }

    public void setOpercode(Object opercode) {
        this.opercode = opercode;
    }

    public Object getOperid() {
        return operid;
    }

    public void setOperid(Object operid) {
        this.operid = operid;
    }

    public Object getOpername() {
        return opername;
    }

    public void setOpername(Object opername) {
        this.opername = opername;
    }

    public Object getOpertype() {
        return opertype;
    }

    public void setOpertype(Object opertype) {
        this.opertype = opertype;
    }

    public String getOwnerStationName() {
        return ownerStationName;
    }

    public void setOwnerStationName(String ownerStationName) {
        this.ownerStationName = ownerStationName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public String getSumid() {
        return sumid;
    }

    public void setSumid(String sumid) {
        this.sumid = sumid;
    }

    public String getTicketBill() {
        return ticketBill;
    }

    public void setTicketBill(String ticketBill) {
        this.ticketBill = ticketBill;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }
}
