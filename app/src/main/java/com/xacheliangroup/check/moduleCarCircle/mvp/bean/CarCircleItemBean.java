package com.xacheliangroup.check.moduleCarCircle.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2018/12/24,13:41
 */
public class CarCircleItemBean extends BaseBean{
    /**
     * isperfect : 1
     * perfect : 1
     * pichight : 2560
     * picurl : http://jzcar.oss-cn-zhangjiakou.aliyuncs.com/post/15402859891561.jpg
     * picwidth : 1440
     * postid : 576
     * posttag : 1
     * title : 7r77ririr
     * username : U26152440179684360
     * userpic :
     */

    private String isperfect;
    private String perfect;
    private String pichight;
    private String picurl;
    private String picwidth;
    private String postid;
    private String posttag;
    private String title;
    private String username;
    private String userpic;

    @Override
    public int getViewType() {
        return ViewItemType.CAR_CIRCLE_HOME_VIEW;
    }


    public String getIsperfect() {
        return isperfect;
    }

    public void setIsperfect(String isperfect) {
        this.isperfect = isperfect;
    }

    public String getPerfect() {
        return perfect;
    }

    public void setPerfect(String perfect) {
        this.perfect = perfect;
    }

    public String getPichight() {
        return pichight;
    }

    public void setPichight(String pichight) {
        this.pichight = pichight;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPicwidth() {
        return picwidth;
    }

    public void setPicwidth(String picwidth) {
        this.picwidth = picwidth;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPosttag() {
        return posttag;
    }

    public void setPosttag(String posttag) {
        this.posttag = posttag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }
}
