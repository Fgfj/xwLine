package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/9/3,15:39
 */
public class ChangeCarTypeBean extends BaseBean {

    /**
     * carid : 3
     * carnumber : 陕A10001
     * carvin : ASSSDF11100011BB1
     * status : 1
     * pdaid : null
     * createtime : 2019-09-02 16:46:02
     * companyid : 1
     * driverid : null
     * bindtype : 1
     * seatnumber : 8
     * types : 6
     */

    private int carid;
    private String carnumber;
    private String carvin;
    private int status;
    private Object pdaid;
    private String createtime;
    private int companyid;
    private Object driverid;
    private int bindtype;
    private int seatnumber;
    private int types;

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

    public String getCarvin() {
        return carvin;
    }

    public void setCarvin(String carvin) {
        this.carvin = carvin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPdaid() {
        return pdaid;
    }

    public void setPdaid(Object pdaid) {
        this.pdaid = pdaid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public Object getDriverid() {
        return driverid;
    }

    public void setDriverid(Object driverid) {
        this.driverid = driverid;
    }

    public int getBindtype() {
        return bindtype;
    }

    public void setBindtype(int bindtype) {
        this.bindtype = bindtype;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
