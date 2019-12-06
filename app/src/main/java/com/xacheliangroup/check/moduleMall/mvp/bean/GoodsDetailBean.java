package com.xacheliangroup.check.moduleMall.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

import java.util.List;

/**
 * author:yz
 * data: 2019/2/25,10:28
 */
public class GoodsDetailBean extends BaseBean{
    /**
     * detailUrl : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdse/2019022714083300003.html
     * mdseId : 112
     * mdsePic : http://jzcar.oss-cn-zhangjiakou.aliyuncs.com/mdse/15291344464781.png
     */

    private String detailUrl;
    private String mdseId;
    private String mdsePic;

    @Override
    public String toString() {
        return "GoodsDeatilBean{" +
                "mdseContent='" + mdseContent + '\'' +
                ", mdsePostage='" + mdsePostage + '\'' +
                ", mdsePrice='" + mdsePrice + '\'' +
                ", mdseTitle='" + mdseTitle + '\'' +
                ", mdseBannerList=" + mdseBannerList +
                ", mdseDetails=" + mdseDetails +
                '}';
    }

    /**
     * mdseBannerList : ["http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/palx/1547110819942.png","http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/palx/1547110805084.png","http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/palx/1547110467193.png"]
     * mdseContent : 米其林轮胎米其林轮胎米其林轮胎米其林轮胎米其林轮胎米其林轮胎米其林轮胎
     * mdseDetails : ["https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/baoxian.png","https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/qicheyongpin.png","https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/qichefuwu.png","https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/guoshu.png"]
     * mdsePostage : 10.00
     * mdsePrice : 500.00
     * mdseTitle : 轮胎
     */

    private String mdseContent;
    private String mdsePostage;
    private String mdsePrice;
    private String mdseTitle;
    private List<String> mdseBannerList;
    private List<String> mdseDetails;

    public String getMdseContent() {
        return mdseContent;
    }

    public void setMdseContent(String mdseContent) {
        this.mdseContent = mdseContent;
    }

    public String getMdsePostage() {
        return mdsePostage;
    }

    public void setMdsePostage(String mdsePostage) {
        this.mdsePostage = mdsePostage;
    }

    public String getMdsePrice() {
        return mdsePrice;
    }

    public void setMdsePrice(String mdsePrice) {
        this.mdsePrice = mdsePrice;
    }

    public String getMdseTitle() {
        return mdseTitle;
    }

    public void setMdseTitle(String mdseTitle) {
        this.mdseTitle = mdseTitle;
    }

    public List<String> getMdseBannerList() {
        return mdseBannerList;
    }

    public void setMdseBannerList(List<String> mdseBannerList) {
        this.mdseBannerList = mdseBannerList;
    }

    public List<String> getMdseDetails() {
        return mdseDetails;
    }

    public void setMdseDetails(List<String> mdseDetails) {
        this.mdseDetails = mdseDetails;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getMdseId() {
        return mdseId;
    }

    public void setMdseId(String mdseId) {
        this.mdseId = mdseId;
    }

    public String getMdsePic() {
        return mdsePic;
    }

    public void setMdsePic(String mdsePic) {
        this.mdsePic = mdsePic;
    }
}
