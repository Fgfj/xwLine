package com.xacheliangroup.check.common.eventbus;

/**
 * author:yz
 * data: 2018/12/14,10:37
 */
public class MessageObj {
    private int itemPostion;
    private int clothId;
    private String content;


    public MessageObj(int itemPostion, int clothId, String content) {
        this.itemPostion = itemPostion;
        this.clothId = clothId;
        this.content = content;
    }

    public int getItemPostion() {
        return itemPostion;
    }

    public void setItemPostion(int itemPostion) {
        this.itemPostion = itemPostion;
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageObj{" +
                "itemPostion=" + itemPostion +
                ", clothId=" + clothId +
                ", content='" + content + '\'' +
                '}';
    }
}
