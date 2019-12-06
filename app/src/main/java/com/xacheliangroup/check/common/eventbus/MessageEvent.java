package com.xacheliangroup.check.common.eventbus;

/**
 * author:yz
 * data: 2018/12/14,10:31
 */
public class MessageEvent {
    private String type;
    private Object object;

    public MessageEvent(String type, Object object) {
        this.type = type;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
