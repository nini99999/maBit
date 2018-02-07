package com.poshist.maBit.utils;

import java.text.DecimalFormat;

/**
 * Created by poshist on 18-1-29.
 */
public class MatchUtils {
    public static String doubleToStr(Double d){
        DecimalFormat df = new DecimalFormat("######.####");
        return df.format(d);

    }
}
