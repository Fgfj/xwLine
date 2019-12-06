package com.xacheliangroup.check.moduleMall.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2019/2/25,09:48
 */
public class GoodsItemBean extends BaseBean {
    /**
     * content : 有红又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果又大的苹果
     * mdseId : 113
     * mdsePic : http://jzcar.oss-cn-zhangjiakou.aliyuncs.com/mdse/15331042722921.jpg
     * price : 200.00
     * title : 橘子
     */

    private String content;
    private int mdseId;
    private String mdsePic;
    private String price;
    private String title;

    @Override
    public int getViewType() {
        return ViewItemType.MARKETING_GOODS_LIST_ITEM_VIEW;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMdseId() {
        return mdseId;
    }

    public void setMdseId(int mdseId) {
        this.mdseId = mdseId;
    }

    public String getMdsePic() {
        return mdsePic;
    }

    public void setMdsePic(String mdsePic) {
        this.mdsePic = mdsePic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
