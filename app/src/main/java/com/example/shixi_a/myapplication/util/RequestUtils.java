package com.example.shixi_a.myapplication.util;


import static com.example.shixi_a.myapplication.util.MD5Utils.getMd5;

/**
 * Created by Shixi-A on 2017/6/2.
 */

public class RequestUtils {


    public static String getTimeStamp(){
        long now = System.currentTimeMillis()/1000;
        return String.valueOf(now);
    }

    public static String getCode(String name, String password, String timestamp){
        return getMd5(name + getMd5(password) + timestamp + "E12AAD9E3CD85");
    }

    public static String getVathomeKey(String password, String timestamp){
        return getMd5(password + "^Vangen-2006$") + timestamp;
    }

    public static String getSecretKey(String token, String opt, String timestamp){
        return getMd5(token + opt + timestamp);
    }
}
