package com.xacheliangroup.check.moduleMine.mvp.bean;

import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.flag.ViewItemType;
import com.xacheliangroup.check.common.type.MineMenuEnum;

/**
 * author:yz
 * data: 2018/12/19,11:36
 */
public class MineHomeUiBean extends BaseBean{
    private boolean isShowGray;
    private boolean isShowLine;
    private int imgId;
    private String title;
    private boolean isShowRedPoint;
    private MineMenuEnum mineMenuEnum;

    public MineMenuEnum getMineMenuEnum() {
        return mineMenuEnum;
    }

    public void setMineMenuEnum(MineMenuEnum mineMenuEnum) {
        this.mineMenuEnum = mineMenuEnum;
    }

    public MineHomeUiBean(boolean isShowGray, boolean isShowLine, int imgId, String title, boolean isShowRedPoint, MineMenuEnum mineMenuEnum) {
        this.isShowGray = isShowGray;
        this.isShowLine = isShowLine;
        this.imgId = imgId;
        this.title = title;
        this.isShowRedPoint = isShowRedPoint;
        this.mineMenuEnum = mineMenuEnum;
    }

    @Override
    public int getViewType() {
        return ViewItemType.MINE_UI_VIEW;
    }

    public MineHomeUiBean(boolean isShowGray, boolean isShowLine, int imgId, String title, boolean isShowRedPoint) {
        this.isShowGray = isShowGray;
        this.isShowLine = isShowLine;
        this.imgId = imgId;
        this.title = title;
        this.isShowRedPoint = isShowRedPoint;
    }

    public boolean isShowRedPoint() {
        return isShowRedPoint;
    }

    public void setShowRedPoint(boolean showRedPoint) {
        isShowRedPoint = showRedPoint;
    }

    public boolean isShowGray() {
        return isShowGray;
    }

    public void setShowGray(boolean showGray) {
        isShowGray = showGray;
    }

    public boolean isShowLine() {
        return isShowLine;
    }

    public void setShowLine(boolean showLine) {
        isShowLine = showLine;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
