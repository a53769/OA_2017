package com.example.shixi_a.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public class StringUtils {
    /**
     * 是否为空
     * @param str 字符串
     * @return true 空 false 非空
     */
    public static Boolean isEmpty(String str) {
        if(str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static String getDate(Date date, String formatType){
        return new SimpleDateFormat(formatType).format(date);
    }

    public static String getSubDate(String str){
        return str.substring(5,str.length() - 3);
    }

}
