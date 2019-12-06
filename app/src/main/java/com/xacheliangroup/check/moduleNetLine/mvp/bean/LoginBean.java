package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/9/2,16:50
 */
public class LoginBean extends BaseBean {

    /**
     * cars : {"carid":6,"carnumber":"陕V90001","carvin":"ASSSDF1110001151","status":1,"pdaid":null,"createtime":"2019-09-02 16:47:26","companyid":2,"driverid":3,"bindtype":2,"seatnumber":8}
     * driver : {"driverid":3,"realname":"王五","mobile":"13683498647","idcard":"610481199104122621","companyid":1,"passwd":"123456","status":1}
     */

    private int recordid;

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    private CarsBean cars;
    private DriverBean driver;

    public CarsBean getCars() {
        return cars;
    }

    public void setCars(CarsBean cars) {
        this.cars = cars;
    }

    public DriverBean getDriver() {
        return driver;
    }

    public void setDriver(DriverBean driver) {
        this.driver = driver;
    }

    public static class CarsBean extends BaseBean{
        @Override
        public String toString() {
            return "CarsBean{" +
                    "carid=" + carid +
                    ", carnumber='" + carnumber + '\'' +
                    ", carvin='" + carvin + '\'' +
                    ", status=" + status +
                    ", pdaid=" + pdaid +
                    ", createtime='" + createtime + '\'' +
                    ", companyid=" + companyid +
                    ", driverid=" + driverid +
                    ", bindtype=" + bindtype +
                    ", seatnumber=" + seatnumber +
                    '}';
        }

        /**
         * carid : 6
         * carnumber : 陕V90001
         * carvin : ASSSDF1110001151
         * status : 1
         * pdaid : null
         * createtime : 2019-09-02 16:47:26
         * companyid : 2
         * driverid : 3
         * bindtype : 2
         * seatnumber : 8
         */

        private int carid;
        private String carnumber;
        private String carvin;
        private int status;
        private Object pdaid;
        private String createtime;
        private int companyid;
        private int driverid;
        private int bindtype;
        private int seatnumber;

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

        public int getDriverid() {
            return driverid;
        }

        public void setDriverid(int driverid) {
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
    }

    public static class DriverBean extends BaseBean{
        /**
         * driverid : 3
         * realname : 王五
         * mobile : 13683498647
         * idcard : 610481199104122621
         * companyid : 1
         * passwd : 123456
         * status : 1
         */

        private int driverid;
        private String realname;
        private String mobile;
        private String idcard;
        private int companyid;
        private String passwd;
        private int status;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getCompanyid() {
            return companyid;
        }

        public void setCompanyid(int companyid) {
            this.companyid = companyid;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
