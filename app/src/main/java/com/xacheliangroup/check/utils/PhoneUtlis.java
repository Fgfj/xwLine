package com.xacheliangroup.check.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author:yz
 * data: 2018/12/25,15:47
 */
public class PhoneUtlis {
    public static boolean isMobileNum(String mobiles) {
        String str= "^1[3|4|5|6|7|8][0-9]{9}$";
        Pattern p = Pattern
                .compile(str);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
