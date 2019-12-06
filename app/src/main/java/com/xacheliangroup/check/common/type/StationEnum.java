package com.xacheliangroup.check.common.type;

import java.io.Serializable;

/**
 * author:yz
 * data: 2019/6/18,11:50
 */
public enum  StationEnum implements Serializable {
    CX("0","城西客运站"),
    FZC("1","纺织城客运站"),
    YL("3","杨凌客运站"),
    SFW("2","三府湾客运站"),
    RELESE("4","正式版");

    StationEnum(String type, String expalin) {
        this.type = type;
        this.expalin = expalin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpalin() {
        return expalin;
    }

    public void setExpalin(String expalin) {
        this.expalin = expalin;
    }

    private String type;
    private String expalin;
}
