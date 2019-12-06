package com.xacheliangroup.check.checkTickets.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

import java.util.List;

/**
 * author:yz
 * data: 2019/6/5,15:24
 */
public class CTCarListBean extends BaseBean {

    /**
     * data : [{"BalanceComp":100262,"Bus":103418,"BusCode":"陕AR2365","CheckCount":3,"CheckSeats":"AAA000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188179,"ClasTime":"19:00","Clascode":"TY920","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257145,"IsAdd":false,"IsFree":true,"LineID":124,"LineName":"汤峪(焦岱)","OwnerComp":100275,"QX":"汤峪","SeatCount":33,"TicketCount":3},{"BalanceComp":13,"Bus":103138,"BusCode":"陕A96873","CheckCount":10,"CheckSeats":"0AAAAAAAAAA00000000000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188225,"ClasTime":"18:45","Clascode":"HX929","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257141,"IsAdd":false,"IsFree":true,"LineID":100212,"LineName":"鄠邑929路","OwnerComp":1301,"QX":"阿姑泉","SeatCount":55,"TicketCount":10},{"BalanceComp":13,"Bus":102142,"BusCode":"陕A83166","CheckCount":12,"CheckSeats":"AAAAAAAAAAAA000000000000000000000","ClasDate":"2019-05-13","ClasID":1188017,"ClasTime":"18:40","Clascode":"HXGS01","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257138,"IsAdd":false,"IsFree":true,"LineID":103,"LineName":"鄠邑电厂(高速)","OwnerComp":1301,"QX":"余下(电影院)","SeatCount":33,"TicketCount":12},{"BalanceComp":100262,"Bus":103424,"BusCode":"陕A2972X","CheckCount":3,"CheckSeats":"AAA000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188179,"ClasTime":"18:40","Clascode":"TY920","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257139,"IsAdd":false,"IsFree":true,"LineID":124,"LineName":"汤峪(焦岱)","OwnerComp":100275,"QX":"汤峪","SeatCount":33,"TicketCount":3},{"BalanceComp":11,"Bus":102189,"BusCode":"陕H10081","CheckCount":30,"CheckSeats":"AA0A0AAAAAAAAAAAAAAAAAAAAAAAAAA0A0000","ClasDate":"2019-05-13","ClasID":1188148,"ClasTime":"18:40","Clascode":"ZA275","Driver1":105063,"Driver2":0,"Gate":5,"HandCount":0,"ID":11257098,"IsAdd":false,"IsFree":false,"LineID":139,"LineName":"镇安(高速)","OwnerComp":1101,"QX":"镇安慧源学校","SeatCount":37,"TicketCount":30},{"BalanceComp":10,"Bus":102288,"BusCode":"陕F32661","CheckCount":15,"CheckSeats":"000AAAAAAAAAAAA00000000000000AAA0000000000","ClasDate":"2019-05-13","ClasID":1188560,"ClasTime":"18:30","Clascode":"+41","Driver1":105063,"Driver2":0,"Gate":2,"HandCount":0,"ID":11257088,"IsAdd":true,"IsFree":false,"LineID":154,"LineName":"西乡(高速)","OwnerComp":1001,"QX":"西乡(高速)","SeatCount":42,"TicketCount":15},{"BalanceComp":13,"Bus":102851,"BusCode":"陕A95366","CheckCount":4,"CheckSeats":"AAAA000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1187978,"ClasTime":"18:30","Clascode":"hk201","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257125,"IsAdd":false,"IsFree":true,"LineID":100202,"LineName":"西鄠快客","OwnerComp":1301,"QX":"鄠邑(快客)","SeatCount":37,"TicketCount":4},{"BalanceComp":13,"Bus":101907,"BusCode":"陕A82943","CheckCount":9,"CheckSeats":"AAAAAAAAA000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188017,"ClasTime":"18:30","Clascode":"HXGS01","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257127,"IsAdd":false,"IsFree":true,"LineID":103,"LineName":"鄠邑电厂(高速)","OwnerComp":1301,"QX":"余下(电影院)","SeatCount":33,"TicketCount":9},{"BalanceComp":100262,"Bus":103403,"BusCode":"陕A2965X","CheckCount":4,"CheckSeats":"AAAA00000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188179,"ClasTime":"18:30","Clascode":"TY920","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257131,"IsAdd":false,"IsFree":true,"LineID":124,"LineName":"汤峪(焦岱)","OwnerComp":100275,"QX":"汤峪","SeatCount":33,"TicketCount":4},{"BalanceComp":13,"Bus":102429,"BusCode":"陕A83652","CheckCount":7,"CheckSeats":"AAAAAAA00000000000000000000000000000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188243,"ClasTime":"18:30","Clascode":"HX928","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257132,"IsAdd":false,"IsFree":true,"LineID":118,"LineName":"鄠邑928路","OwnerComp":1301,"QX":"鄠邑(928低速)","SeatCount":69,"TicketCount":7},{"BalanceComp":13,"Bus":103150,"BusCode":"陕A96706","CheckCount":3,"CheckSeats":"0AAA000000000000000000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188225,"ClasTime":"18:20","Clascode":"HX929","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257124,"IsAdd":false,"IsFree":true,"LineID":100212,"LineName":"鄠邑929路","OwnerComp":1301,"QX":"阿姑泉","SeatCount":55,"TicketCount":3},{"BalanceComp":13,"Bus":101904,"BusCode":"陕A82732","CheckCount":11,"CheckSeats":"AAAAAAAAAAA0000000000000000000000","ClasDate":"2019-05-13","ClasID":1188017,"ClasTime":"18:20","Clascode":"HXGS01","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257122,"IsAdd":false,"IsFree":true,"LineID":103,"LineName":"鄠邑电厂(高速)","OwnerComp":1301,"QX":"余下(电影院)","SeatCount":33,"TicketCount":11},{"BalanceComp":13,"Bus":103369,"BusCode":"陕AW3165","CheckCount":22,"CheckSeats":"AAAAAAAAAAAAAAAA0AAAAAA00000000000","ClasDate":"2019-05-13","ClasID":1187978,"ClasTime":"18:20","Clascode":"hk201","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257114,"IsAdd":false,"IsFree":true,"LineID":100202,"LineName":"西鄠快客","OwnerComp":1301,"QX":"鄠邑(快客)","SeatCount":34,"TicketCount":22},{"BalanceComp":100262,"Bus":103476,"BusCode":"陕A2951X","CheckCount":4,"CheckSeats":"AAAA0000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188179,"ClasTime":"18:15","Clascode":"TY920","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257120,"IsAdd":false,"IsFree":true,"LineID":124,"LineName":"汤峪(焦岱)","OwnerComp":100275,"QX":"汤峪","SeatCount":35,"TicketCount":4},{"BalanceComp":13,"Bus":103134,"BusCode":"陕A96985","CheckCount":16,"CheckSeats":"AAAAAAAAAAA0AAAAA00000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188225,"ClasTime":"18:10","Clascode":"HX929","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257112,"IsAdd":false,"IsFree":true,"LineID":100212,"LineName":"鄠邑929路","OwnerComp":1301,"QX":"阿姑泉","SeatCount":55,"TicketCount":16},{"BalanceComp":13,"Bus":101924,"BusCode":"陕A83060","CheckCount":14,"CheckSeats":"AAAAAAAAAAAAAA0000000000000000000","ClasDate":"2019-05-13","ClasID":1188017,"ClasTime":"18:10","Clascode":"HXGS01","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257113,"IsAdd":false,"IsFree":true,"LineID":103,"LineName":"鄠邑电厂(高速)","OwnerComp":1301,"QX":"余下(电影院)","SeatCount":33,"TicketCount":14},{"BalanceComp":13,"Bus":102428,"BusCode":"陕A83561","CheckCount":9,"CheckSeats":"AAAAAAAAA000000000000000000000000000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188243,"ClasTime":"18:10","Clascode":"HX928","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257123,"IsAdd":false,"IsFree":true,"LineID":118,"LineName":"鄠邑928路","OwnerComp":1301,"QX":"鄠邑(928低速)","SeatCount":69,"TicketCount":9},{"BalanceComp":13,"Bus":102146,"BusCode":"陕A83171","CheckCount":3,"CheckSeats":"AAA000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188017,"ClasTime":"18:00","Clascode":"HXGS01","Driver1":105063,"Driver2":0,"Gate":6,"HandCount":0,"ID":11257110,"IsAdd":false,"IsFree":true,"LineID":103,"LineName":"鄠邑电厂(高速)","OwnerComp":1301,"QX":"余下(电影院)","SeatCount":33,"TicketCount":3},{"BalanceComp":49,"Bus":103305,"BusCode":"陕AW1590","CheckCount":7,"CheckSeats":"AAAAAAA000000000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1188014,"ClasTime":"18:00","Clascode":"305I","Driver1":105063,"Driver2":0,"Gate":4,"HandCount":0,"ID":11257075,"IsAdd":false,"IsFree":false,"LineID":177,"LineName":"旬阳(高速)","OwnerComp":4901,"QX":"旬阳(高速)","SeatCount":46,"TicketCount":7},{"BalanceComp":2,"Bus":103277,"BusCode":"陕H10149","CheckCount":29,"CheckSeats":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAA0000000000000000","ClasDate":"2019-05-13","ClasID":1188205,"ClasTime":"18:00","Clascode":"SZ0211","Driver1":105063,"Driver2":0,"Gate":5,"HandCount":0,"ID":11257046,"IsAdd":false,"IsFree":false,"LineID":100270,"LineName":"商州(商洛)","OwnerComp":201,"QX":"商洛","SeatCount":45,"TicketCount":29},{"BalanceComp":26,"Bus":999,"BusCode":"陕G08988","CheckCount":25,"CheckSeats":"AAAAAAAAAAAAAAAAAAAAAAAAA00000000000","ClasDate":"2019-05-13","ClasID":1188010,"ClasTime":"18:00","Clascode":"349G","Driver1":105063,"Driver2":0,"Gate":2,"HandCount":0,"ID":11257068,"IsAdd":false,"IsFree":false,"LineID":158,"LineName":"宁陕(高速)","OwnerComp":26,"QX":"宁陕(高速)","SeatCount":36,"TicketCount":25},{"BalanceComp":50,"Bus":102887,"BusCode":"陕G20536","CheckCount":13,"CheckSeats":"AAAAAAAAAAAAA00000000000000000000000","ClasDate":"2019-05-13","ClasID":1188182,"ClasTime":"17:30","Clascode":"309H","Driver1":105063,"Driver2":0,"Gate":3,"HandCount":0,"ID":11257066,"IsAdd":false,"IsFree":false,"LineID":178,"LineName":"平利(高速)","OwnerComp":5001,"QX":"平利","SeatCount":36,"TicketCount":13},{"BalanceComp":100256,"Bus":813,"BusCode":"陕E77980","CheckCount":4,"CheckSeats":"AAAA000000000000000000000000000000000","ClasDate":"2019-05-13","ClasID":1196203,"ClasTime":"17:30","Clascode":"WHY107","Driver1":105063,"Driver2":0,"Gate":2,"HandCount":0,"ID":11257074,"IsAdd":false,"IsFree":false,"LineID":100271,"LineName":"华阴(华山)","OwnerComp":100269,"QX":"华阴(华山)","SeatCount":37,"TicketCount":4},{"BalanceComp":50,"Bus":103503,"BusCode":"陝G32115","CheckCount":46,"CheckSeats":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","ClasDate":"2019-05-13","ClasID":1188194,"ClasTime":"17:00","Clascode":"A337","Driver1":105063,"Driver2":0,"Gate":3,"HandCount":0,"ID":11257071,"IsAdd":false,"IsFree":false,"LineID":175,"LineName":"安康(高速)","OwnerComp":5001,"QX":"安康(高速)","SeatCount":46,"TicketCount":46},{"BalanceComp":22,"Bus":102905,"BusCode":"陕H11309","CheckCount":13,"CheckSeats":"A0AAAAAAAAAAAA00000000000000000000000","ClasDate":"2019-05-13","ClasID":1188211,"ClasTime":"17:00","Clascode":"LN228","Driver1":105063,"Driver2":0,"Gate":5,"HandCount":0,"ID":11257057,"IsAdd":false,"IsFree":false,"LineID":164,"LineName":"洛南(高速)","OwnerComp":2201,"QX":"洛南(高速)","SeatCount":37,"TicketCount":13},{"BalanceComp":44,"Bus":102806,"BusCode":"陕EB0801","CheckCount":19,"CheckSeats":"AAAAAAAAAAAAAAAAAAA0000000000","ClasDate":"2019-05-13","ClasID":1187975,"ClasTime":"17:00","Clascode":"231H","Driver1":105063,"Driver2":0,"Gate":5,"HandCount":0,"ID":11257062,"IsAdd":false,"IsFree":false,"LineID":121,"LineName":"金堆城","OwnerComp":4401,"QX":"金堆城","SeatCount":29,"TicketCount":19},{"BalanceComp":100243,"Bus":102511,"BusCode":"陕E78339","CheckCount":19,"CheckSeats":"AAAAAAAAAAAAAAAAAAA000000000000000000","ClasDate":"2019-05-13","ClasID":1196144,"ClasTime":"16:50","Clascode":"WN107","Driver1":105063,"Driver2":0,"Gate":2,"HandCount":0,"ID":11257048,"IsAdd":false,"IsFree":false,"LineID":301,"LineName":"渭南","OwnerComp":100255,"QX":"渭南","SeatCount":37,"TicketCount":19},{"BalanceComp":4,"Bus":101483,"BusCode":"陕AK2249","CheckCount":22,"CheckSeats":"AAAAAAAAAAAAAAAAAAAAAA000000000000000","ClasDate":"2019-05-13","ClasID":1188190,"ClasTime":"16:40","Clascode":"AS529","Driver1":105063,"Driver2":0,"Gate":2,"HandCount":0,"ID":11257056,"IsAdd":false,"IsFree":false,"LineID":157,"LineName":"石泉(高速)","OwnerComp":401,"QX":"石泉(高速)","SeatCount":37,"TicketCount":22},{"BalanceComp":50,"Bus":103379,"BusCode":"陕G32660","CheckCount":32,"CheckSeats":"AA0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA0000000000000","ClasDate":"2019-05-13","ClasID":1188239,"ClasTime":"16:20","Clascode":"A335","Driver1":105063,"Driver2":0,"Gate":3,"HandCount":0,"ID":11257041,"IsAdd":false,"IsFree":false,"LineID":175,"LineName":"安康(高速)","OwnerComp":5001,"QX":"安康(高速)","SeatCount":46,"TicketCount":32}]
     * stationname : 纺织城客运站
     */

    private String stationname;
    private List<DataBean> data;



    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean  extends BaseBean{
        @Override
        public int getViewType() {
            return ViewItemType.CHECK_TICKETS_CAR_LIST;
        }
        /**
         * BalanceComp : 100262
         * Bus : 103418
         * BusCode : 陕AR2365
         * CheckCount : 3
         * CheckSeats : AAA000000000000000000000000000000
         * ClasDate : 2019-05-13
         * ClasID : 1188179
         * ClasTime : 19:00
         * Clascode : TY920
         * Driver1 : 105063
         * Driver2 : 0
         * Gate : 6
         * HandCount : 0
         * ID : 11257145
         * IsAdd : false
         * IsFree : true
         * LineID : 124
         * LineName : 汤峪(焦岱)
         * OwnerComp : 100275
         * QX : 汤峪
         * SeatCount : 33
         * TicketCount : 3
         */

        private int BalanceComp;
        private int Bus;
        private String BusCode;
        private int CheckCount;
        private String CheckSeats;
        private String ClasDate;
        private int ClasID;
        private String ClasTime;
        private String Clascode;
        private int Driver1;
        private int Driver2;
        private int Gate;
        private int HandCount;
        private int ID;
        private boolean IsAdd;
        private boolean IsFree;
        private int LineID;
        private String LineName;
        private int OwnerComp;
        private String QX;
        private int SeatCount;
        private int TicketCount;

        private String type;
        private String stationName;

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getBalanceComp() {
            return BalanceComp;
        }

        public void setBalanceComp(int BalanceComp) {
            this.BalanceComp = BalanceComp;
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

        public int getCheckCount() {
            return CheckCount;
        }

        public void setCheckCount(int CheckCount) {
            this.CheckCount = CheckCount;
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

        public int getDriver1() {
            return Driver1;
        }

        public void setDriver1(int Driver1) {
            this.Driver1 = Driver1;
        }

        public int getDriver2() {
            return Driver2;
        }

        public void setDriver2(int Driver2) {
            this.Driver2 = Driver2;
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

        public String getQX() {
            return QX;
        }

        public void setQX(String QX) {
            this.QX = QX;
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
    }
}
