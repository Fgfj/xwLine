package com.xacheliangroup.check.checkTickets.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2019/6/6,09:35
 */
public class CTIdOrQrcodeCheckBean extends BaseBean {
    /**
     * data : {"ArriveName":"韩城高速","BarCode":"0923261046","BasePrice":60,"BookOpAddressName":"城西微信","BookOpTime":"2019-07-02T09:49:21.663","BookPayAddressName":"城西微信","BookPayTime":"2019-07-02T09:50:31.58","CheckGate":{},"Child":0,"ChildIDCard":"","ChildName":"","ChildOperName":{},"ChildType":{},"ClasCode":"HCG","ClasID":350671,"Clasdatetime":"2019-07-02 19:30","ClassID":"","ErrorID":0,"ErrorMSG":"该售票明细已经正常.可以检票.售票时间2019-07-02 09:49:52售票人：SPJ_14","Flag":"0","HaveChild":"","ID":23261188,"IDType":1,"IDTypeName":"售票明细","InsurancedIDCard":"610581198709082214","InsurancedIDType":"身份证","InsurancedName":"薛晓强","IsFee":"","IsFree":1,"IsLoss":{},"LineID":105,"LineName":"韩城高速","NetBookNo":"6041254","NetDPTicketNO":"D433265999","Notes":"","OpTime":"2019-07-02T09:49:52.067","OperID":84,"OperName":"售票机14","OwnerStationName":"纺织城客运站","Price":61.5,"PropName":"网付","RePrintFlagName":"未补打","RePrintOper":{},"RePrintOperName":{},"RePrintOperTime":{},"Seat":1,"StatusName":"未检","TicketBill":"51661932","TicketTypeName":"全票","oilFee":0,"paytypename":{},"stFee":1.5}
     * type : 3
     */

    private DataBean data;
    private int type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class DataBean {
        /**
         * ArriveName : 韩城高速
         * BarCode : 0923261046
         * BasePrice : 60
         * BookOpAddressName : 城西微信
         * BookOpTime : 2019-07-02T09:49:21.663
         * BookPayAddressName : 城西微信
         * BookPayTime : 2019-07-02T09:50:31.58
         * CheckGate : {}
         * Child : 0
         * ChildIDCard :
         * ChildName :
         * ChildOperName : {}
         * ChildType : {}
         * ClasCode : HCG
         * ClasID : 350671
         * Clasdatetime : 2019-07-02 19:30
         * ClassID :
         * ErrorID : 0
         * ErrorMSG : 该售票明细已经正常.可以检票.售票时间2019-07-02 09:49:52售票人：SPJ_14
         * Flag : 0
         * HaveChild :
         * ID : 23261188
         * IDType : 1
         * IDTypeName : 售票明细
         * InsurancedIDCard : 610581198709082214
         * InsurancedIDType : 身份证
         * InsurancedName : 薛晓强
         * IsFee :
         * IsFree : 1
         * IsLoss : {}
         * LineID : 105
         * LineName : 韩城高速
         * NetBookNo : 6041254
         * NetDPTicketNO : D433265999
         * Notes :
         * OpTime : 2019-07-02T09:49:52.067
         * OperID : 84
         * OperName : 售票机14
         * OwnerStationName : 纺织城客运站
         * Price : 61.5
         * PropName : 网付
         * RePrintFlagName : 未补打
         * RePrintOper : {}
         * RePrintOperName : {}
         * RePrintOperTime : {}
         * Seat : 1
         * StatusName : 未检
         * TicketBill : 51661932
         * TicketTypeName : 全票
         * oilFee : 0
         * paytypename : {}
         * stFee : 1.5
         */

        private String ArriveName;
        private String BarCode;
        private int BasePrice;
//        private String BookOpAddressName;
//        private String BookOpTime;
//        private String BookPayAddressName;
//        private String BookPayTime;
//        private CheckGateBean CheckGate;
        private int Child;
//        private String ChildIDCard;
//        private String ChildName;
//        private ChildOperNameBean ChildOperName;
//        private ChildTypeBean ChildType;
        private String ClasCode;
        private int ClasID;
        private String Clasdatetime;
        private String ClassID;
        private int ErrorID;
        private String ErrorMSG;
        private String Flag;
        private String HaveChild;
        private int ID;
        private int IDType;
        private String IDTypeName;
        private String InsurancedIDCard;
        private String InsurancedIDType;
        private String InsurancedName;
        private String IsFee;
        private int IsFree;
        private IsLossBean IsLoss;
        private int LineID;
        private String LineName;
//        private String NetBookNo;
//        private String NetDPTicketNO;
        private String Notes;
        private String OpTime;
        private int OperID;
        private String OperName;
        private String OwnerStationName;
        private double Price;
        private String PropName;
        private String RePrintFlagName;
        private RePrintOperBean RePrintOper;
        private RePrintOperNameBean RePrintOperName;
        private RePrintOperTimeBean RePrintOperTime;
        private int Seat;
        private String StatusName;
        private String TicketBill;
        private String TicketTypeName;
        private int oilFee;
//        private PaytypenameBean paytypename;
        private double stFee;
        private String checkBill;

        public String getCheckBill() {
            return checkBill;
        }

        public void setCheckBill(String checkBill) {
            this.checkBill = checkBill;
        }

        public String getArriveName() {
            return ArriveName;
        }

        public void setArriveName(String ArriveName) {
            this.ArriveName = ArriveName;
        }

        public String getBarCode() {
            return BarCode;
        }

        public void setBarCode(String BarCode) {
            this.BarCode = BarCode;
        }

        public int getBasePrice() {
            return BasePrice;
        }

        public void setBasePrice(int BasePrice) {
            this.BasePrice = BasePrice;
        }

//        public String getBookOpAddressName() {
//            return BookOpAddressName;
//        }
//
//        public void setBookOpAddressName(String BookOpAddressName) {
//            this.BookOpAddressName = BookOpAddressName;
//        }
//
//        public String getBookOpTime() {
//            return BookOpTime;
//        }
//
//        public void setBookOpTime(String BookOpTime) {
//            this.BookOpTime = BookOpTime;
//        }
//
//        public String getBookPayAddressName() {
//            return BookPayAddressName;
//        }
//
//        public void setBookPayAddressName(String BookPayAddressName) {
//            this.BookPayAddressName = BookPayAddressName;
//        }

//        public String getBookPayTime() {
//            return BookPayTime;
//        }
//
//        public void setBookPayTime(String BookPayTime) {
//            this.BookPayTime = BookPayTime;
//        }

//        public CheckGateBean getCheckGate() {
//            return CheckGate;
//        }
//
//        public void setCheckGate(CheckGateBean CheckGate) {
//            this.CheckGate = CheckGate;
//        }

        public int getChild() {
            return Child;
        }

        public void setChild(int Child) {
            this.Child = Child;
        }

//        public String getChildIDCard() {
//            return ChildIDCard;
//        }
//
//        public void setChildIDCard(String ChildIDCard) {
//            this.ChildIDCard = ChildIDCard;
//        }
//
//        public String getChildName() {
//            return ChildName;
//        }
//
//        public void setChildName(String ChildName) {
//            this.ChildName = ChildName;
//        }
//
//        public ChildOperNameBean getChildOperName() {
//            return ChildOperName;
//        }
//
//        public void setChildOperName(ChildOperNameBean ChildOperName) {
//            this.ChildOperName = ChildOperName;
//        }

//        public ChildTypeBean getChildType() {
//            return ChildType;
//        }
//
//        public void setChildType(ChildTypeBean ChildType) {
//            this.ChildType = ChildType;
//        }

        public String getClasCode() {
            return ClasCode;
        }

        public void setClasCode(String ClasCode) {
            this.ClasCode = ClasCode;
        }

        public int getClasID() {
            return ClasID;
        }

        public void setClasID(int ClasID) {
            this.ClasID = ClasID;
        }

        public String getClasdatetime() {
            return Clasdatetime;
        }

        public void setClasdatetime(String Clasdatetime) {
            this.Clasdatetime = Clasdatetime;
        }

        public String getClassID() {
            return ClassID;
        }

        public void setClassID(String ClassID) {
            this.ClassID = ClassID;
        }

        public int getErrorID() {
            return ErrorID;
        }

        public void setErrorID(int ErrorID) {
            this.ErrorID = ErrorID;
        }

        public String getErrorMSG() {
            return ErrorMSG;
        }

        public void setErrorMSG(String ErrorMSG) {
            this.ErrorMSG = ErrorMSG;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
        }

        public String getHaveChild() {
            return HaveChild;
        }

        public void setHaveChild(String HaveChild) {
            this.HaveChild = HaveChild;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getIDType() {
            return IDType;
        }

        public void setIDType(int IDType) {
            this.IDType = IDType;
        }

        public String getIDTypeName() {
            return IDTypeName;
        }

        public void setIDTypeName(String IDTypeName) {
            this.IDTypeName = IDTypeName;
        }

        public String getInsurancedIDCard() {
            return InsurancedIDCard;
        }

        public void setInsurancedIDCard(String InsurancedIDCard) {
            this.InsurancedIDCard = InsurancedIDCard;
        }

        public String getInsurancedIDType() {
            return InsurancedIDType;
        }

        public void setInsurancedIDType(String InsurancedIDType) {
            this.InsurancedIDType = InsurancedIDType;
        }

        public String getInsurancedName() {
            return InsurancedName;
        }

        public void setInsurancedName(String InsurancedName) {
            this.InsurancedName = InsurancedName;
        }

        public String getIsFee() {
            return IsFee;
        }

        public void setIsFee(String IsFee) {
            this.IsFee = IsFee;
        }

        public int getIsFree() {
            return IsFree;
        }

        public void setIsFree(int IsFree) {
            this.IsFree = IsFree;
        }

        public IsLossBean getIsLoss() {
            return IsLoss;
        }

        public void setIsLoss(IsLossBean IsLoss) {
            this.IsLoss = IsLoss;
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

//        public String getNetBookNo() {
//            return NetBookNo;
//        }
//
//        public void setNetBookNo(String NetBookNo) {
//            this.NetBookNo = NetBookNo;
//        }
//
//        public String getNetDPTicketNO() {
//            return NetDPTicketNO;
//        }
//
//        public void setNetDPTicketNO(String NetDPTicketNO) {
//            this.NetDPTicketNO = NetDPTicketNO;
//        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String Notes) {
            this.Notes = Notes;
        }

        public String getOpTime() {
            return OpTime;
        }

        public void setOpTime(String OpTime) {
            this.OpTime = OpTime;
        }

        public int getOperID() {
            return OperID;
        }

        public void setOperID(int OperID) {
            this.OperID = OperID;
        }

        public String getOperName() {
            return OperName;
        }

        public void setOperName(String OperName) {
            this.OperName = OperName;
        }

        public String getOwnerStationName() {
            return OwnerStationName;
        }

        public void setOwnerStationName(String OwnerStationName) {
            this.OwnerStationName = OwnerStationName;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getPropName() {
            return PropName;
        }

        public void setPropName(String PropName) {
            this.PropName = PropName;
        }

        public String getRePrintFlagName() {
            return RePrintFlagName;
        }

        public void setRePrintFlagName(String RePrintFlagName) {
            this.RePrintFlagName = RePrintFlagName;
        }

        public RePrintOperBean getRePrintOper() {
            return RePrintOper;
        }

        public void setRePrintOper(RePrintOperBean RePrintOper) {
            this.RePrintOper = RePrintOper;
        }

        public RePrintOperNameBean getRePrintOperName() {
            return RePrintOperName;
        }

        public void setRePrintOperName(RePrintOperNameBean RePrintOperName) {
            this.RePrintOperName = RePrintOperName;
        }

        public RePrintOperTimeBean getRePrintOperTime() {
            return RePrintOperTime;
        }

        public void setRePrintOperTime(RePrintOperTimeBean RePrintOperTime) {
            this.RePrintOperTime = RePrintOperTime;
        }

        public int getSeat() {
            return Seat;
        }

        public void setSeat(int Seat) {
            this.Seat = Seat;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public String getTicketBill() {
            return TicketBill;
        }

        public void setTicketBill(String TicketBill) {
            this.TicketBill = TicketBill;
        }

        public String getTicketTypeName() {
            return TicketTypeName;
        }

        public void setTicketTypeName(String TicketTypeName) {
            this.TicketTypeName = TicketTypeName;
        }

        public int getOilFee() {
            return oilFee;
        }

        public void setOilFee(int oilFee) {
            this.oilFee = oilFee;
        }

//        public PaytypenameBean getPaytypename() {
//            return paytypename;
//        }

//        public void setPaytypename(PaytypenameBean paytypename) {
//            this.paytypename = paytypename;
//        }

        public double getStFee() {
            return stFee;
        }

        public void setStFee(double stFee) {
            this.stFee = stFee;
        }

        public static class CheckGateBean {
        }

        public static class ChildOperNameBean {
        }

        public static class ChildTypeBean {
        }

        public static class IsLossBean {
        }

        public static class RePrintOperBean {
        }

        public static class RePrintOperNameBean {
        }

        public static class RePrintOperTimeBean {
        }

        public static class PaytypenameBean {
        }
    }
//    /**
//     * data : {"ArriveName":"韩城高速","BarCode":"0923261046","BasePrice":60,"BookOpAddressName":"城西微信","BookOpTime":"2019-07-02T09:49:21.663","BookPayAddressName":"城西微信","BookPayTime":"2019-07-02T09:50:31.58","CheckGate":{},"Child":0,"ChildIDCard":"","ChildName":"","ChildOperName":{},"ChildType":{},"ClasCode":"HCG","ClasID":350671,"Clasdatetime":"2019-07-02 19:30","ClassID":"","ErrorID":0,"ErrorMSG":"该售票明细已经正常.可以检票.售票时间2019-07-02 09:49:52售票人：SPJ_14","Flag":"0","HaveChild":"","ID":23261188,"IDType":1,"IDTypeName":"售票明细","InsurancedIDCard":"610581198709082214","InsurancedIDType":"身份证","InsurancedName":"薛晓强","IsFee":"","IsFree":1,"IsLoss":{},"LineID":105,"LineName":"韩城高速","NetBookNo":"6041254","NetDPTicketNO":"D433265999","Notes":"","OpTime":"2019-07-02T09:49:52.067","OperID":84,"OperName":"售票机14","OwnerStationName":"纺织城客运站","Price":61.5,"PropName":"网付","RePrintFlagName":"未补打","RePrintOper":{},"RePrintOperName":{},"RePrintOperTime":{},"Seat":1,"StatusName":"未检","TicketBill":"51661932","TicketTypeName":"全票","oilFee":0,"paytypename":{},"stFee":1.5}
//     * type : 3
//     */
//
//    private DataBean data;
//    private int type;

//    /**
//     * data : {"ArriveName":"汉阴(高速)","BarCode":"0241203409","BasePrice":85,"BookOpAddressName":{},"BookOpTime":{},"BookPayAddressName":{},"BookPayTime":{},"CheckGate":{},"Child":1,"ChildIDCard":{},"ChildName":{},"ChildOperName":"韩蕊","ChildType":{},"ClasCode":"307K","ClasID":1188193,"Clasdatetime":"2019-05-13 18:00","ClassID":"","ErrorID":0,"ErrorMSG":"该售票明细状态是：正常.可以检票.售票时间2019-05-13 16:47:31售票人：042","Flag":"0","HaveChild":"免童","ID":41193444,"IDType":1,"IDTypeName":"售票明细","InsurancedIDCard":"610621199110190028","InsurancedIDType":"身份证","InsurancedName":"李雪梅","IsFee":"","IsFree":0,"IsLoss":{},"LineID":176,"LineName":"汉阴(高速)","NetBookNo":{},"NetDPTicketNO":{},"Notes":"","OpTime":"2019-05-13T16:47:31.207","OperID":101231,"OperName":"韩蕊","OwnerStationName":"城南客运站","Price":86.5,"PropName":"售票","RePrintFlagName":"未补打","RePrintOper":{},"RePrintOperName":{},"RePrintOperTime":{},"Seat":7,"StatusName":"未检","TicketBill":"14760141","TicketTypeName":"全票","oilFee":0,"paytypename":"现金","stFee":1.5}
//     * type : 1
//     */
//
//    private DataBean data;
//    private int type;
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public static class DataBean {
//        /**
//         * ArriveName : 汉阴(高速)
//         * BarCode : 0241203409
//         * BasePrice : 85
//         * BookOpAddressName : {}
//         * BookOpTime : {}
//         * BookPayAddressName : {}
//         * BookPayTime : {}
//         * CheckGate : {}
//         * Child : 1
//         * ChildIDCard : {}
//         * ChildName : {}
//         * ChildOperName : 韩蕊
//         * ChildType : {}
//         * ClasCode : 307K
//         * ClasID : 1188193
//         * Clasdatetime : 2019-05-13 18:00
//         * ClassID :
//         * ErrorID : 0
//         * ErrorMSG : 该售票明细状态是：正常.可以检票.售票时间2019-05-13 16:47:31售票人：042
//         * Flag : 0
//         * HaveChild : 免童
//         * ID : 41193444
//         * IDType : 1
//         * IDTypeName : 售票明细
//         * InsurancedIDCard : 610621199110190028
//         * InsurancedIDType : 身份证
//         * InsurancedName : 李雪梅
//         * IsFee :
//         * IsFree : 0
//         * IsLoss : {}
//         * LineID : 176
//         * LineName : 汉阴(高速)
//         * NetBookNo : {}
//         * NetDPTicketNO : {}
//         * Notes :
//         * OpTime : 2019-05-13T16:47:31.207
//         * OperID : 101231
//         * OperName : 韩蕊
//         * OwnerStationName : 城南客运站
//         * Price : 86.5
//         * PropName : 售票
//         * RePrintFlagName : 未补打
//         * RePrintOper : {}
//         * RePrintOperName : {}
//         * RePrintOperTime : {}
//         * Seat : 7
//         * StatusName : 未检
//         * TicketBill : 14760141
//         * TicketTypeName : 全票
//         * oilFee : 0
//         * paytypename : 现金
//         * stFee : 1.5
//         */
//
//        private String ArriveName;
//        private String BarCode;
//        private int BasePrice;
//        private BookOpAddressNameBean BookOpAddressName;
//        private BookOpTimeBean BookOpTime;
//        private BookPayAddressNameBean BookPayAddressName;
//        private BookPayTimeBean BookPayTime;
//        private CheckGateBean CheckGate;
//        private int Child;
//        private ChildIDCardBean ChildIDCard;
//        private ChildNameBean ChildName;
//        private String ChildOperName;
//        private ChildTypeBean ChildType;
//        private String ClasCode;
//        private int ClasID;
//        private String Clasdatetime;
//        private String ClassID;
//        private int ErrorID;
//        private String ErrorMSG;
//        private String Flag;
//        private String HaveChild;
//        private int ID;
//        private int IDType;
//        private String IDTypeName;
//        private String InsurancedIDCard;
//        private String InsurancedIDType;
//        private String InsurancedName;
//        private String IsFee;
//        private int IsFree;
//        private IsLossBean IsLoss;
//        private int LineID;
//        private String LineName;
//        private NetBookNoBean NetBookNo;
//        private NetDPTicketNOBean NetDPTicketNO;
//        private String Notes;
//        private String OpTime;
//        private int OperID;
//        private String OperName;
//        private String OwnerStationName;
//        private double Price;
//        private String PropName;
//        private String RePrintFlagName;
//        private RePrintOperBean RePrintOper;
//        private RePrintOperNameBean RePrintOperName;
//        private RePrintOperTimeBean RePrintOperTime;
//        private int Seat;
//        private String StatusName;
//        private String TicketBill;
//        private String TicketTypeName;
//        private int oilFee;
//        private String paytypename;
//        private double stFee;
//
//        public String getArriveName() {
//            return ArriveName;
//        }
//
//        public void setArriveName(String ArriveName) {
//            this.ArriveName = ArriveName;
//        }
//
//        public String getBarCode() {
//            return BarCode;
//        }
//
//        public void setBarCode(String BarCode) {
//            this.BarCode = BarCode;
//        }
//
//        public int getBasePrice() {
//            return BasePrice;
//        }
//
//        public void setBasePrice(int BasePrice) {
//            this.BasePrice = BasePrice;
//        }
//
//        public BookOpAddressNameBean getBookOpAddressName() {
//            return BookOpAddressName;
//        }
//
//        public void setBookOpAddressName(BookOpAddressNameBean BookOpAddressName) {
//            this.BookOpAddressName = BookOpAddressName;
//        }
//
//        public BookOpTimeBean getBookOpTime() {
//            return BookOpTime;
//        }
//
//        public void setBookOpTime(BookOpTimeBean BookOpTime) {
//            this.BookOpTime = BookOpTime;
//        }
//
//        public BookPayAddressNameBean getBookPayAddressName() {
//            return BookPayAddressName;
//        }
//
//        public void setBookPayAddressName(BookPayAddressNameBean BookPayAddressName) {
//            this.BookPayAddressName = BookPayAddressName;
//        }
//
//        public BookPayTimeBean getBookPayTime() {
//            return BookPayTime;
//        }
//
//        public void setBookPayTime(BookPayTimeBean BookPayTime) {
//            this.BookPayTime = BookPayTime;
//        }
//
//        public CheckGateBean getCheckGate() {
//            return CheckGate;
//        }
//
//        public void setCheckGate(CheckGateBean CheckGate) {
//            this.CheckGate = CheckGate;
//        }
//
//        public int getChild() {
//            return Child;
//        }
//
//        public void setChild(int Child) {
//            this.Child = Child;
//        }
//
//        public ChildIDCardBean getChildIDCard() {
//            return ChildIDCard;
//        }
//
//        public void setChildIDCard(ChildIDCardBean ChildIDCard) {
//            this.ChildIDCard = ChildIDCard;
//        }
//
//        public ChildNameBean getChildName() {
//            return ChildName;
//        }
//
//        public void setChildName(ChildNameBean ChildName) {
//            this.ChildName = ChildName;
//        }
//
//        public String getChildOperName() {
//            return ChildOperName;
//        }
//
//        public void setChildOperName(String ChildOperName) {
//            this.ChildOperName = ChildOperName;
//        }
//
//        public ChildTypeBean getChildType() {
//            return ChildType;
//        }
//
//        public void setChildType(ChildTypeBean ChildType) {
//            this.ChildType = ChildType;
//        }
//
//        public String getClasCode() {
//            return ClasCode;
//        }
//
//        public void setClasCode(String ClasCode) {
//            this.ClasCode = ClasCode;
//        }
//
//        public int getClasID() {
//            return ClasID;
//        }
//
//        public void setClasID(int ClasID) {
//            this.ClasID = ClasID;
//        }
//
//        public String getClasdatetime() {
//            return Clasdatetime;
//        }
//
//        public void setClasdatetime(String Clasdatetime) {
//            this.Clasdatetime = Clasdatetime;
//        }
//
//        public String getClassID() {
//            return ClassID;
//        }
//
//        public void setClassID(String ClassID) {
//            this.ClassID = ClassID;
//        }
//
//        public int getErrorID() {
//            return ErrorID;
//        }
//
//        public void setErrorID(int ErrorID) {
//            this.ErrorID = ErrorID;
//        }
//
//        public String getErrorMSG() {
//            return ErrorMSG;
//        }
//
//        public void setErrorMSG(String ErrorMSG) {
//            this.ErrorMSG = ErrorMSG;
//        }
//
//        public String getFlag() {
//            return Flag;
//        }
//
//        public void setFlag(String Flag) {
//            this.Flag = Flag;
//        }
//
//        public String getHaveChild() {
//            return HaveChild;
//        }
//
//        public void setHaveChild(String HaveChild) {
//            this.HaveChild = HaveChild;
//        }
//
//        public int getID() {
//            return ID;
//        }
//
//        public void setID(int ID) {
//            this.ID = ID;
//        }
//
//        public int getIDType() {
//            return IDType;
//        }
//
//        public void setIDType(int IDType) {
//            this.IDType = IDType;
//        }
//
//        public String getIDTypeName() {
//            return IDTypeName;
//        }
//
//        public void setIDTypeName(String IDTypeName) {
//            this.IDTypeName = IDTypeName;
//        }
//
//        public String getInsurancedIDCard() {
//            return InsurancedIDCard;
//        }
//
//        public void setInsurancedIDCard(String InsurancedIDCard) {
//            this.InsurancedIDCard = InsurancedIDCard;
//        }
//
//        public String getInsurancedIDType() {
//            return InsurancedIDType;
//        }
//
//        public void setInsurancedIDType(String InsurancedIDType) {
//            this.InsurancedIDType = InsurancedIDType;
//        }
//
//        public String getInsurancedName() {
//            return InsurancedName;
//        }
//
//        public void setInsurancedName(String InsurancedName) {
//            this.InsurancedName = InsurancedName;
//        }
//
//        public String getIsFee() {
//            return IsFee;
//        }
//
//        public void setIsFee(String IsFee) {
//            this.IsFee = IsFee;
//        }
//
//        public int getIsFree() {
//            return IsFree;
//        }
//
//        public void setIsFree(int IsFree) {
//            this.IsFree = IsFree;
//        }
//
//        public IsLossBean getIsLoss() {
//            return IsLoss;
//        }
//
//        public void setIsLoss(IsLossBean IsLoss) {
//            this.IsLoss = IsLoss;
//        }
//
//        public int getLineID() {
//            return LineID;
//        }
//
//        public void setLineID(int LineID) {
//            this.LineID = LineID;
//        }
//
//        public String getLineName() {
//            return LineName;
//        }
//
//        public void setLineName(String LineName) {
//            this.LineName = LineName;
//        }
//
//        public NetBookNoBean getNetBookNo() {
//            return NetBookNo;
//        }
//
//        public void setNetBookNo(NetBookNoBean NetBookNo) {
//            this.NetBookNo = NetBookNo;
//        }
//
//        public NetDPTicketNOBean getNetDPTicketNO() {
//            return NetDPTicketNO;
//        }
//
//        public void setNetDPTicketNO(NetDPTicketNOBean NetDPTicketNO) {
//            this.NetDPTicketNO = NetDPTicketNO;
//        }
//
//        public String getNotes() {
//            return Notes;
//        }
//
//        public void setNotes(String Notes) {
//            this.Notes = Notes;
//        }
//
//        public String getOpTime() {
//            return OpTime;
//        }
//
//        public void setOpTime(String OpTime) {
//            this.OpTime = OpTime;
//        }
//
//        public int getOperID() {
//            return OperID;
//        }
//
//        public void setOperID(int OperID) {
//            this.OperID = OperID;
//        }
//
//        public String getOperName() {
//            return OperName;
//        }
//
//        public void setOperName(String OperName) {
//            this.OperName = OperName;
//        }
//
//        public String getOwnerStationName() {
//            return OwnerStationName;
//        }
//
//        public void setOwnerStationName(String OwnerStationName) {
//            this.OwnerStationName = OwnerStationName;
//        }
//
//        public double getPrice() {
//            return Price;
//        }
//
//        public void setPrice(double Price) {
//            this.Price = Price;
//        }
//
//        public String getPropName() {
//            return PropName;
//        }
//
//        public void setPropName(String PropName) {
//            this.PropName = PropName;
//        }
//
//        public String getRePrintFlagName() {
//            return RePrintFlagName;
//        }
//
//        public void setRePrintFlagName(String RePrintFlagName) {
//            this.RePrintFlagName = RePrintFlagName;
//        }
//
//        public RePrintOperBean getRePrintOper() {
//            return RePrintOper;
//        }
//
//        public void setRePrintOper(RePrintOperBean RePrintOper) {
//            this.RePrintOper = RePrintOper;
//        }
//
//        public RePrintOperNameBean getRePrintOperName() {
//            return RePrintOperName;
//        }
//
//        public void setRePrintOperName(RePrintOperNameBean RePrintOperName) {
//            this.RePrintOperName = RePrintOperName;
//        }
//
//        public RePrintOperTimeBean getRePrintOperTime() {
//            return RePrintOperTime;
//        }
//
//        public void setRePrintOperTime(RePrintOperTimeBean RePrintOperTime) {
//            this.RePrintOperTime = RePrintOperTime;
//        }
//
//        public int getSeat() {
//            return Seat;
//        }
//
//        public void setSeat(int Seat) {
//            this.Seat = Seat;
//        }
//
//        public String getStatusName() {
//            return StatusName;
//        }
//
//        public void setStatusName(String StatusName) {
//            this.StatusName = StatusName;
//        }
//
//        public String getTicketBill() {
//            return TicketBill;
//        }
//
//        public void setTicketBill(String TicketBill) {
//            this.TicketBill = TicketBill;
//        }
//
//        public String getTicketTypeName() {
//            return TicketTypeName;
//        }
//
//        public void setTicketTypeName(String TicketTypeName) {
//            this.TicketTypeName = TicketTypeName;
//        }
//
//        public int getOilFee() {
//            return oilFee;
//        }
//
//        public void setOilFee(int oilFee) {
//            this.oilFee = oilFee;
//        }
//
//        public String getPaytypename() {
//            return paytypename;
//        }
//
//        public void setPaytypename(String paytypename) {
//            this.paytypename = paytypename;
//        }
//
//        public double getStFee() {
//            return stFee;
//        }
//
//        public void setStFee(double stFee) {
//            this.stFee = stFee;
//        }
//
//        public static class BookOpAddressNameBean {
//        }
//
//        public static class BookOpTimeBean {
//        }
//
//        public static class BookPayAddressNameBean {
//        }
//
//        public static class BookPayTimeBean {
//        }
//
//        public static class CheckGateBean {
//        }
//
//        public static class ChildIDCardBean {
//        }
//
//        public static class ChildNameBean {
//        }
//
//        public static class ChildTypeBean {
//        }
//
//        public static class IsLossBean {
//        }
//
//        public static class NetBookNoBean {
//        }
//
//        public static class NetDPTicketNOBean {
//        }
//
//        public static class RePrintOperBean {
//        }
//
//        public static class RePrintOperNameBean {
//        }
//
//        public static class RePrintOperTimeBean {
//        }
//    }






}
