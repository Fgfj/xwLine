package com.xacheliangroup.check.moduleMall.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;

import java.util.List;

/**
 * author:yz
 * data: 2018/12/14,14:53
 */
public class MallHeadBean extends BaseBean {

    private List<FuncationalBean> funcational;
    private List<HeadlinesBean> headlines;
    private List<ShufflingBean> shuffling;
    private List<SuperrecommendBean> superrecommend;

    public List<FuncationalBean> getFuncational() {
        return funcational;
    }

    public void setFuncational(List<FuncationalBean> funcational) {
        this.funcational = funcational;
    }

    public List<HeadlinesBean> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<HeadlinesBean> headlines) {
        this.headlines = headlines;
    }

    public List<ShufflingBean> getShuffling() {
        return shuffling;
    }

    public void setShuffling(List<ShufflingBean> shuffling) {
        this.shuffling = shuffling;
    }

    public List<SuperrecommendBean> getSuperrecommend() {
        return superrecommend;
    }

    public void setSuperrecommend(List<SuperrecommendBean> superrecommend) {
        this.superrecommend = superrecommend;
    }

    public static class FuncationalBean extends BaseBean{
        @Override
        public String toString() {
            return "FuncationalBean{" +
                    "funPic='" + funPic + '\'' +
                    ", funTitle='" + funTitle + '\'' +
                    ", gameId='" + gameId + '\'' +
                    ", isShowTitle='" + isShowTitle + '\'' +
                    ", linkUrl='" + linkUrl + '\'' +
                    ", mdseTypeId='" + mdseTypeId + '\'' +
                    ", topDes='" + topDes + '\'' +
                    ", topPicUrl='" + topPicUrl + '\'' +
                    ", topTitle='" + topTitle + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        @Override
        public int getViewType() {
            return ViewItemType.MALL_HOME_FOUR_FUN_ITEM_VIEW;
        }

        /**
         * funPic : https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/Insurance.png
         * funTitle : 汽车保险
         * gameId :
         * isShowTitle : 1
         * linkUrl :
         * mdseTypeId :
         * topDes :
         * topPicUrl :
         * topTitle :
         * type : 1
         */

        private String funPic;
        private String funTitle;
        private String gameId;
        private String isShowTitle;
        private String linkUrl;
        private String mdseTypeId;
        private String topDes;
        private String topPicUrl;
        private String topTitle;
        private String type;//1.保险 2.商家列表 3.商品列表)

        public String getFunPic() {
            return funPic;
        }

        public void setFunPic(String funPic) {
            this.funPic = funPic;
        }

        public String getFunTitle() {
            return funTitle;
        }

        public void setFunTitle(String funTitle) {
            this.funTitle = funTitle;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getIsShowTitle() {
            return isShowTitle;
        }

        public void setIsShowTitle(String isShowTitle) {
            this.isShowTitle = isShowTitle;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getMdseTypeId() {
            return mdseTypeId;
        }

        public void setMdseTypeId(String mdseTypeId) {
            this.mdseTypeId = mdseTypeId;
        }

        public String getTopDes() {
            return topDes;
        }

        public void setTopDes(String topDes) {
            this.topDes = topDes;
        }

        public String getTopPicUrl() {
            return topPicUrl;
        }

        public void setTopPicUrl(String topPicUrl) {
            this.topPicUrl = topPicUrl;
        }

        public String getTopTitle() {
            return topTitle;
        }

        public void setTopTitle(String topTitle) {
            this.topTitle = topTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class HeadlinesBean extends BaseBean{
        /**
         * gameId : 1
         * mdseTypeId :
         * newstitle : 汽车美容家！！！
         * topDes : 为您优选2KM汽车服务
         * topPicUrl : https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/qichefuwu.png
         * topTitle : 汽车服务
         * type : 5
         * typeId :
         * webUrl :
         */

        private String gameId;
        private String mdseTypeId;
        private String newstitle;
        private String topDes;
        private String topPicUrl;
        private String topTitle;
        private String type;
        private String typeId;
        private String webUrl;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getMdseTypeId() {
            return mdseTypeId;
        }

        public void setMdseTypeId(String mdseTypeId) {
            this.mdseTypeId = mdseTypeId;
        }

        public String getNewstitle() {
            return newstitle;
        }

        public void setNewstitle(String newstitle) {
            this.newstitle = newstitle;
        }

        public String getTopDes() {
            return topDes;
        }

        public void setTopDes(String topDes) {
            this.topDes = topDes;
        }

        public String getTopPicUrl() {
            return topPicUrl;
        }

        public void setTopPicUrl(String topPicUrl) {
            this.topPicUrl = topPicUrl;
        }

        public String getTopTitle() {
            return topTitle;
        }

        public void setTopTitle(String topTitle) {
            this.topTitle = topTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }

    public static class ShufflingBean extends BaseBean{
        /**
         * gameId :
         * mdseTypeId :
         * pic : http://47.92.114.238:21001/4s_file/image/banner/1/1527485240750.png
         * title : 轮播1
         * topDes :
         * topPicUrl :
         * topTitle :
         * type : 1
         * typeId : 112
         * webUrl :
         */

        private String gameId;
        private String mdseTypeId;
        private String pic;
        private String title;
        private String topDes;
        private String topPicUrl;
        private String topTitle;
        private String type;
        private String typeId;
        private String webUrl;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getMdseTypeId() {
            return mdseTypeId;
        }

        public void setMdseTypeId(String mdseTypeId) {
            this.mdseTypeId = mdseTypeId;
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

        public String getTopDes() {
            return topDes;
        }

        public void setTopDes(String topDes) {
            this.topDes = topDes;
        }

        public String getTopPicUrl() {
            return topPicUrl;
        }

        public void setTopPicUrl(String topPicUrl) {
            this.topPicUrl = topPicUrl;
        }

        public String getTopTitle() {
            return topTitle;
        }

        public void setTopTitle(String topTitle) {
            this.topTitle = topTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }

    public static class SuperrecommendBean extends BaseBean{
        /**
         * gameId : 1
         * mdseTypeId :
         * pic : http://jzctest.oss-cn-zhangjiakou.aliyuncs.com/programtitle/1545202333068.jpg
         * topDes : 为您优选2KM汽车服务
         * topPicUrl : https://jzctest.oss-cn-zhangjiakou.aliyuncs.com/mdsetype/qichefuwu.png
         * topTitle : 汽车服务
         * type : 5
         * typeId :
         * webUrl :
         */

        private String gameId;
        private String mdseTypeId;
        private String pic;
        private String topDes;
        private String topPicUrl;
        private String topTitle;
        private String type;
        private String typeId;
        private String webUrl;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getMdseTypeId() {
            return mdseTypeId;
        }

        public void setMdseTypeId(String mdseTypeId) {
            this.mdseTypeId = mdseTypeId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTopDes() {
            return topDes;
        }

        public void setTopDes(String topDes) {
            this.topDes = topDes;
        }

        public String getTopPicUrl() {
            return topPicUrl;
        }

        public void setTopPicUrl(String topPicUrl) {
            this.topPicUrl = topPicUrl;
        }

        public String getTopTitle() {
            return topTitle;
        }

        public void setTopTitle(String topTitle) {
            this.topTitle = topTitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }
    }
}
