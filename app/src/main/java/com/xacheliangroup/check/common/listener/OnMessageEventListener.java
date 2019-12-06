package com.xacheliangroup.check.common.listener;

import com.xacheliangroup.check.common.eventbus.MessageEvent;

import javax.annotation.Nonnull;

/**
 * author:yz
 * data: 2018/12/14,10:30
 */
public interface OnMessageEventListener {
    void onMessageEventHandle(@Nonnull MessageEvent messageEvent);
}
