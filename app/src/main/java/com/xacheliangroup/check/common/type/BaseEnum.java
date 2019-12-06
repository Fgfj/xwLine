package com.xacheliangroup.check.common.type;

/**
 * author:yz
 * data: 2018/12/14,13:36
 */
public enum BaseEnum {
    A(1,"1"),
    B(2,"2");

    private int value;
    private String explain;

    BaseEnum(int value, String explain) {
        this.value = value;
        this.explain = explain;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
