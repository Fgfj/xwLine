package com.xacheliangroup.check.common.luban;

import java.util.List;

/**
 * Created by admin on 2018/4/16.
 */

public interface LuBanListener {
    void success(List<String> list);
    void fail();
}
