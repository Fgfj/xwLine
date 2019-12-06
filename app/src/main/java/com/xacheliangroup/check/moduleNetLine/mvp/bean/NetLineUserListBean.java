package com.xacheliangroup.check.moduleNetLine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

import java.util.List;

/**
 * author:yz
 * data: 2019/9/2,15:50
 */
public class NetLineUserListBean extends BaseBean {

    /**
     * driverid : 3
     * carnumber : 陕A10001
     * rename : 王五
     * seatnumber : 8
     * mobile : 13683498647
     * busnumber : 0
     * palxSlorderList : [{"orderid":19,"userid":9,"openid":"oZGE_5fndXGSI1cBP33fimBqTsng","createtime":"2019-08-31 15:52:36","orderprice":50,"starcity":"西安","starcityid":1,"staradress":"西安市·五路口","starcoords":"34.276359,108.969331","endcity":"渭南","endcityid":2,"endaddress":"渭南市·陕西电大(渭南分校)","endcoords":"34.494308,109.46814","startdate":"08月31日","starttimefirst":"16:30","starttimesecond":"17:00","status":1,"type":1,"ordernumber":"PALX2019083115523600001","paymoney":null,"paytime":null,"updatetime":null,"backmoney":null,"backpoundage":null,"lineid":null,"carid":null,"driverid":null,"ordermobile":"17681552082","waterno":null,"arrivetime":null,"hxtime":null,"remark":"要发票,赶飞机,有老人","recordid":8,"peoplenum":0,"usernames":null,"ticket":null,"palxSldriver":null,"palxSlcar":null,"comment":null,"realname":null,"mobile":null,"idcard":null,"carnumber":null}]
     * carid : 3
     * status : 1
     */

    private String recordid;
    private int driverid;
    private String carnumber;
    private String rename;
    private int seatnumber;
    private String mobile;
    private int busnumber;
    private int carid;
    private int status;
    private List<PalxSlorderListBean> palxSlorderList;

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getRename() {
        return rename;
    }

    public void setRename(String rename) {
        this.rename = rename;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(int busnumber) {
        this.busnumber = busnumber;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PalxSlorderListBean> getPalxSlorderList() {
        return palxSlorderList;
    }

    public void setPalxSlorderList(List<PalxSlorderListBean> palxSlorderList) {
        this.palxSlorderList = palxSlorderList;
    }

    /**
     * The type Palx slorder list bean.
     */
    public static class PalxSlorderListBean extends BaseBean{
        @Override
        public int getViewType() {
            return ViewItemType.NET_LINE_ITEM_LIST;
        }

        /**
         * orderid : 19
         * userid : 9
         * openid : oZGE_5fndXGSI1cBP33fimBqTsng
         * createtime : 2019-08-31 15:52:36
         * orderprice : 50
         * starcity : 西安
         * starcityid : 1
         * staradress : 西安市·五路口
         * starcoords : 34.276359,108.969331
         * endcity : 渭南
         * endcityid : 2
         * endaddress : 渭南市·陕西电大(渭南分校)
         * endcoords : 34.494308,109.46814
         * startdate : 08月31日
         * starttimefirst : 16:30
         * starttimesecond : 17:00
         * status : 1
         * type : 1
         * ordernumber : PALX2019083115523600001
         * paymoney : null
         * paytime : null
         * updatetime : null
         * backmoney : null
         * backpoundage : null
         * lineid : null
         * carid : null
         * driverid : null
         * ordermobile : 17681552082
         * waterno : null
         * arrivetime : null
         * hxtime : null
         * remark : 要发票,赶飞机,有老人
         * recordid : 8
         * peoplenum : 0
         * usernames : null
         * ticket : null
         * palxSldriver : null
         * palxSlcar : null
         * comment : null
         * realname : null
         * mobile : null
         * idcard : null
         * carnumber : null
         */

        private int orderid;
        private int userid;
        private String openid;
        private String createtime;
        private int orderprice;
        private String starcity;
        private int starcityid;
        private String staradress;
        private String starcoords;
        private String endcity;
        private int endcityid;
        private String endaddress;
        private String endcoords;
        private String startdate;
        private String starttimefirst;
        private String starttimesecond;
        private int status;
        private int type;
        private String ordernumber;
        private Object paymoney;
        private Object paytime;
        private Object updatetime;
        private Object backmoney;
        private Object backpoundage;
        private Object lineid;
        private Object carid;
        private Object driverid;
        private String ordermobile;
        private Object waterno;
        private String arrivetime;
        private Object hxtime;
        private String remark;
        private int recordid;
        private int peoplenum;
        private String usernames;
        private Object ticket;
        private Object palxSldriver;
        private Object palxSlcar;
        private Object comment;
        private Object realname;
        private String mobile;
        private Object idcard;
        private Object carnumber;

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(int orderprice) {
            this.orderprice = orderprice;
        }

        public String getStarcity() {
            return starcity;
        }

        public void setStarcity(String starcity) {
            this.starcity = starcity;
        }

        public int getStarcityid() {
            return starcityid;
        }

        public void setStarcityid(int starcityid) {
            this.starcityid = starcityid;
        }

        public String getStaradress() {
            return staradress;
        }

        public void setStaradress(String staradress) {
            this.staradress = staradress;
        }

        public String getStarcoords() {
            return starcoords;
        }

        public void setStarcoords(String starcoords) {
            this.starcoords = starcoords;
        }

        public String getEndcity() {
            return endcity;
        }

        public void setEndcity(String endcity) {
            this.endcity = endcity;
        }

        public int getEndcityid() {
            return endcityid;
        }

        public void setEndcityid(int endcityid) {
            this.endcityid = endcityid;
        }

        public String getEndaddress() {
            return endaddress;
        }

        public void setEndaddress(String endaddress) {
            this.endaddress = endaddress;
        }

        public String getEndcoords() {
            return endcoords;
        }

        public void setEndcoords(String endcoords) {
            this.endcoords = endcoords;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getStarttimefirst() {
            return starttimefirst;
        }

        public void setStarttimefirst(String starttimefirst) {
            this.starttimefirst = starttimefirst;
        }

        public String getStarttimesecond() {
            return starttimesecond;
        }

        public void setStarttimesecond(String starttimesecond) {
            this.starttimesecond = starttimesecond;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public Object getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(Object paymoney) {
            this.paymoney = paymoney;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }

        public Object getBackmoney() {
            return backmoney;
        }

        public void setBackmoney(Object backmoney) {
            this.backmoney = backmoney;
        }

        public Object getBackpoundage() {
            return backpoundage;
        }

        public void setBackpoundage(Object backpoundage) {
            this.backpoundage = backpoundage;
        }

        public Object getLineid() {
            return lineid;
        }

        public void setLineid(Object lineid) {
            this.lineid = lineid;
        }

        public Object getCarid() {
            return carid;
        }

        public void setCarid(Object carid) {
            this.carid = carid;
        }

        public Object getDriverid() {
            return driverid;
        }

        public void setDriverid(Object driverid) {
            this.driverid = driverid;
        }

        public String getOrdermobile() {
            return ordermobile;
        }

        public void setOrdermobile(String ordermobile) {
            this.ordermobile = ordermobile;
        }

        public Object getWaterno() {
            return waterno;
        }

        public void setWaterno(Object waterno) {
            this.waterno = waterno;
        }



        public Object getHxtime() {
            return hxtime;
        }

        public String getArrivetime() {
            return arrivetime;
        }

        public void setArrivetime(String arrivetime) {
            this.arrivetime = arrivetime;
        }

        public void setHxtime(Object hxtime) {
            this.hxtime = hxtime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public int getPeoplenum() {
            return peoplenum;
        }

        public void setPeoplenum(int peoplenum) {
            this.peoplenum = peoplenum;
        }

        public String getUsernames() {
            return usernames;
        }

        public void setUsernames(String usernames) {
            this.usernames = usernames;
        }

        public Object getTicket() {
            return ticket;
        }

        public void setTicket(Object ticket) {
            this.ticket = ticket;
        }

        public Object getPalxSldriver() {
            return palxSldriver;
        }

        public void setPalxSldriver(Object palxSldriver) {
            this.palxSldriver = palxSldriver;
        }

        public Object getPalxSlcar() {
            return palxSlcar;
        }

        public void setPalxSlcar(Object palxSlcar) {
            this.palxSlcar = palxSlcar;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
        }

        public Object getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(Object carnumber) {
            this.carnumber = carnumber;
        }
    }
}
