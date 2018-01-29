package com.poshist.maBit.utils;

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
}
