package com.xacheliangroup.check.moduleCarComment.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

/**
 * author:yz
 * data: 2018/12/19,16:17
 */
public class CarCommentBean extends BaseBean {

    @Override
    public int getViewType() {
        return ViewItemType.CAR_COMMENT_HOME_VIEW;
    }

    /**
     * createtime : 2018-11-29 15:06:50
     * link : https://v.chezhanri.com/187-533-1.html
     * pic : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/programtitle/1543479777179.jpg
     * title : 西安车展2018
     * type : 1
     */


    private String createtime;
    private String link;
    private String pic;
    private String title;
    private String type;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
