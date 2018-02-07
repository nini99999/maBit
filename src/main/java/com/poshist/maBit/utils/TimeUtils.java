package com.poshist.maBit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by poshist on 18-1-28.
 */
public class TimeUtils {
    public static String getNowYYYYMMDD(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());

    }
    public static String getNowYYYYMMDDHHMMSS(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static Date stringToDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
