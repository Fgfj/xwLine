package com.xacheliangroup.check.common.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;

/**
 * author:yz
 * data: 2018/12/14,13:48
 */
public class AdverBean extends BaseBean {

    /**
     * id : 5
     * linkurl :
     * pic : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/programtitle/1543901991452.jpg
     * title : 公益广告4
     */

    private int id;
    private String linkurl;
    private String pic;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
