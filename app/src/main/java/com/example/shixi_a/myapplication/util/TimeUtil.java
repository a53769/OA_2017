package com.example.shixi_a.myapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by a5376 on 2017/7/6.
 */

public class TimeUtil {

    public static long getTimesnoon(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis()/1000;
    }


    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// + 600000
        return formatter.format(curDate);
    }

    public static Date getCurrentDate(){
        Date curDate = new Date(System.currentTimeMillis() + 600000);
        return curDate;
    }

    public static Date String2Date(String str){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = sim.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
