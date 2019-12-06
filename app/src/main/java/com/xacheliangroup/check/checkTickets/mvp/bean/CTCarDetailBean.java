package com.xacheliangroup.check.checkTickets.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/6/5,17:56
 */
public class CTCarDetailBean extends BaseBean {

    /**
     * Amount : 522
     * BagMoney : 0
     * BalanceComp : 13
     * BalanceCompName : 户县公司
     * Bus : 102853
     * BusCode : 陕A95390
     * CanSale : 1
     * CheckChildNum : 0
     * CheckCount : 36
     * CheckMix : 0
     * CheckSeats : AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA0
     * ClasDate : 2019-05-13T00:00:00
     * ClasID : 1187978
     * ClasTime : 18:50
     * Clascode : hk201
     * DPNum : 0
     * Driver1 : 105063
     * Driver1Name : 肖永波
     * Driver2 : 0
     * Driver2Name : {}
     * Gate : 6
     * HandCount : 0
     * HandMoney : 0
     * ID : 11257142
     * IsAdd : false
     * IsFree : true
     * LineID : 100202
     * LineName : 西鄠快客
     * OwnerComp : 1301
     * OwnerCompName : 户县公司
     * PackMoney : 0
     * QNO1 : 610121197703257391
     * QNO2 : {}
     * QX : 鄠邑(快客)
     * SaleChild : 0
     * SaleNum : 36
     * SeatCount : 37
     * TicketCount : 36
     */



    private double Amount;
    private int BagMoney;
    private int BalanceComp;
    private String BalanceCompName;
    private int Bus;
    private String BusCode;
    private int CanSale;
    private int CheckChildNum;
    private int CheckCount;
    private int CheckMix;
    private String CheckSeats;
    private String ClasDate;
    private int ClasID;
    private String ClasTime;
    private String Clascode;
    private int DPNum;
    private int Driver1;
    private String Driver1Name;
    private int Driver2;
    private Driver2NameBean Driver2Name;
    private int Gate;
    private int HandCount;
    private int HandMoney;
    private int ID;
    private boolean IsAdd;
    private boolean IsFree;
    private int LineID;
    private String LineName;
    private int OwnerComp;
    private String OwnerCompName;
    private int PackMoney;
    private String QNO1;
    private QNO2Bean QNO2;
    private String QX;
    private int SaleChild;
    private int SaleNum;
    private int SeatCount;
    private int TicketCount;

    private int idtype;

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public boolean isAdd() {
        return IsAdd;
    }

    public void setAdd(boolean add) {
        IsAdd = add;
    }

    public boolean isFree() {
        return IsFree;
    }

    public void setFree(boolean free) {
        IsFree = free;
    }

    public int getBagMoney() {
        return BagMoney;
    }

    public void setBagMoney(int BagMoney) {
        this.BagMoney = BagMoney;
    }

    public int getBalanceComp() {
        return BalanceComp;
    }

    public void setBalanceComp(int BalanceComp) {
        this.BalanceComp = BalanceComp;
    }

    public String getBalanceCompName() {
        return BalanceCompName;
    }

    public void setBalanceCompName(String BalanceCompName) {
        this.BalanceCompName = BalanceCompName;
    }

    public int getBus() {
        return Bus;
    }

    public void setBus(int Bus) {
        this.Bus = Bus;
    }

    public String getBusCode() {
        return BusCode;
    }

    public void setBusCode(String BusCode) {
        this.BusCode = BusCode;
    }

    public int getCanSale() {
        return CanSale;
    }

    public void setCanSale(int CanSale) {
        this.CanSale = CanSale;
    }

    public int getCheckChildNum() {
        return CheckChildNum;
    }

    public void setCheckChildNum(int CheckChildNum) {
        this.CheckChildNum = CheckChildNum;
    }

    public int getCheckCount() {
        return CheckCount;
    }

    public void setCheckCount(int CheckCount) {
        this.CheckCount = CheckCount;
    }

    public int getCheckMix() {
        return CheckMix;
    }

    public void setCheckMix(int CheckMix) {
        this.CheckMix = CheckMix;
    }

    public String getCheckSeats() {
        return CheckSeats;
    }

    public void setCheckSeats(String CheckSeats) {
        this.CheckSeats = CheckSeats;
    }

    public String getClasDate() {
        return ClasDate;
    }

    public void setClasDate(String ClasDate) {
        this.ClasDate = ClasDate;
    }

    public int getClasID() {
        return ClasID;
    }

    public void setClasID(int ClasID) {
        this.ClasID = ClasID;
    }

    public String getClasTime() {
        return ClasTime;
    }

    public void setClasTime(String ClasTime) {
        this.ClasTime = ClasTime;
    }

    public String getClascode() {
        return Clascode;
    }

    public void setClascode(String Clascode) {
        this.Clascode = Clascode;
    }

    public int getDPNum() {
        return DPNum;
    }

    public void setDPNum(int DPNum) {
        this.DPNum = DPNum;
    }

    public int getDriver1() {
        return Driver1;
    }

    public void setDriver1(int Driver1) {
        this.Driver1 = Driver1;
    }

    public String getDriver1Name() {
        return Driver1Name;
    }

    public void setDriver1Name(String Driver1Name) {
        this.Driver1Name = Driver1Name;
    }

    public int getDriver2() {
        return Driver2;
    }

    public void setDriver2(int Driver2) {
        this.Driver2 = Driver2;
    }

    public Driver2NameBean getDriver2Name() {
        return Driver2Name;
    }

    public void setDriver2Name(Driver2NameBean Driver2Name) {
        this.Driver2Name = Driver2Name;
    }

    public int getGate() {
        return Gate;
    }

    public void setGate(int Gate) {
        this.Gate = Gate;
    }

    public int getHandCount() {
        return HandCount;
    }

    public void setHandCount(int HandCount) {
        this.HandCount = HandCount;
    }

    public int getHandMoney() {
        return HandMoney;
    }

    public void setHandMoney(int HandMoney) {
        this.HandMoney = HandMoney;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isIsAdd() {
        return IsAdd;
    }

    public void setIsAdd(boolean IsAdd) {
        this.IsAdd = IsAdd;
    }

    public boolean isIsFree() {
        return IsFree;
    }

    public void setIsFree(boolean IsFree) {
        this.IsFree = IsFree;
    }

    public int getLineID() {
        return LineID;
    }

    public void setLineID(int LineID) {
        this.LineID = LineID;
    }

    public String getLineName() {
        return LineName;
    }

    public void setLineName(String LineName) {
        this.LineName = LineName;
    }

    public int getOwnerComp() {
        return OwnerComp;
    }

    public void setOwnerComp(int OwnerComp) {
        this.OwnerComp = OwnerComp;
    }

    public String getOwnerCompName() {
        return OwnerCompName;
    }

    public void setOwnerCompName(String OwnerCompName) {
        this.OwnerCompName = OwnerCompName;
    }

    public int getPackMoney() {
        return PackMoney;
    }

    public void setPackMoney(int PackMoney) {
        this.PackMoney = PackMoney;
    }

    public String getQNO1() {
        return QNO1;
    }

    public void setQNO1(String QNO1) {
        this.QNO1 = QNO1;
    }

    public QNO2Bean getQNO2() {
        return QNO2;
    }

    public void setQNO2(QNO2Bean QNO2) {
        this.QNO2 = QNO2;
    }

    public String getQX() {
        return QX;
    }

    public void setQX(String QX) {
        this.QX = QX;
    }

    public int getSaleChild() {
        return SaleChild;
    }

    public void setSaleChild(int SaleChild) {
        this.SaleChild = SaleChild;
    }

    public int getSaleNum() {
        return SaleNum;
    }

    public void setSaleNum(int SaleNum) {
        this.SaleNum = SaleNum;
    }

    public int getSeatCount() {
        return SeatCount;
    }

    public void setSeatCount(int SeatCount) {
        this.SeatCount = SeatCount;
    }

    public int getTicketCount() {
        return TicketCount;
    }

    public void setTicketCount(int TicketCount) {
        this.TicketCount = TicketCount;
    }

    public static class Driver2NameBean {
    }

    public static class QNO2Bean {
    }
}
